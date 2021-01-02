package com.el.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.el.entity.ElProduct;
import com.el.entity.Member;
import com.el.mapper.MemberMapper;
import com.el.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 会员表  服务实现类
 * </p>
 *
 * @author superJar
 * @since 2021-01-01
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Resource
    private MemberMapper memberMapper;

    @Override
    public IPage<Member> page(Integer pageNum, Integer pageSize, String name, String nickname) {
        QueryWrapper<Member> wrapper = new QueryWrapper<>();
        wrapper.like("NAME", name);
        wrapper.or();
        wrapper.like("NICKNAME", nickname);
        IPage<Member> page = new Page<>(pageNum, pageSize);
        return memberMapper.selectPage(page, wrapper);
    }

    @Transactional
    @Override
    public Integer addOrEdit(Member member) {
        return member.getId() == null ? memberMapper.insert(member) : memberMapper.updateById(member);
    }

    @Transactional
    @Override
    public boolean topUp(Member member) {
        //Amount from frontEnd
        BigDecimal topUpAmount = member.getTopUpAmount();
        float amount = topUpAmount.intValue();

        //obj from db
        Member memberFromDB = memberMapper.selectById(member.getId());
        BigDecimal sumOfTopUpFromDB = memberFromDB.getSumOfTopUp();
        BigDecimal balanceFromDB = memberFromDB.getBalance();
        BigDecimal additionalBalanceFromDB = memberFromDB.getAdditionalBalance();

        //充值总额
        //充值余额增加
        //附加充值余额增加
        Integer additional = 0;
        additional = this.getAdditionalAmount(additional,amount);

        memberFromDB.setAdditionalBalance(additionalBalanceFromDB.add(new BigDecimal(additional)));
        memberFromDB.setSumOfTopUp(sumOfTopUpFromDB.add(topUpAmount));
        memberFromDB.setBalance(balanceFromDB.add(topUpAmount));
        int count = memberMapper.updateById(memberFromDB);

        return count > 0;
    }



    @Transactional
    @Override
    public boolean consume(Member member) {
        //获取前端传的products并解析
        List<ElProduct> products = member.getProducts();
        if (CollectionUtils.isEmpty(products)) {
            throw new RuntimeException("系统检测到该会员没选取任何商品进行消费，因此无法进行消费操作！");
        }
        //找到数据库里的member
        Member memberFromDB = memberMapper.selectById(member.getId());
        float extraBalanceFromDB = memberFromDB.getAdditionalBalance().floatValue();
        float balanceFromDB = memberFromDB.getBalance().floatValue();

        if(memberFromDB.getAdditionalBalance().intValue() == 0 && memberFromDB.getBalance().intValue() == 0){
            throw new RuntimeException("该用户已经没钱扣了！");
        }

        //判断用户余额是否足以扣除本次消费
        DoubleSummaryStatistics sum = products.stream().collect(Collectors.summarizingDouble(
                product -> product.getPrice().add(product.getAdditionalPrice()).floatValue()
        ));

        double sumOfExpenditure = sum.getSum();

        //如果本次消费不够扣，需要返回错误信息给前端
        if((extraBalanceFromDB + balanceFromDB) < sumOfExpenditure){
            throw new RuntimeException("该会员目前的余额不足以扣除本次消费，" +
                    "扣除完余额后还需要支付："
                    +((extraBalanceFromDB + balanceFromDB) - sumOfExpenditure)
                    +"元");
        }

        //拿附加余额抵扣
        if(extraBalanceFromDB > 0){
            if(sumOfExpenditure > extraBalanceFromDB){
                //如果附加余额不够抵扣本次消费
                sumOfExpenditure -= extraBalanceFromDB;
                balanceFromDB -= sumOfExpenditure;
                memberFromDB.setBalance(new BigDecimal(balanceFromDB));
                memberFromDB.setAdditionalBalance(new BigDecimal(0));
            }else{
                extraBalanceFromDB -= sumOfExpenditure;
                memberFromDB.setAdditionalBalance(new BigDecimal(extraBalanceFromDB));
            }
        }

        int count = memberMapper.updateById(memberFromDB);

        return count > 0;
    }


    /**
     * 封装方法：获取所有
     * @param additional
     * @param amount
     * @return
     */
    private Integer getAdditionalAmount(Integer additional, float amount) {
        if(amount < 500){
            return additional;
        }

        if (amount >= 500 && amount < 1000) {
            additional += 50;
        } else if (amount >= 1000 && amount < 2000) {
            additional += 150;
        } else if (amount >= 2000 && amount < 3000) {
            additional += 400;
        } else if (amount >= 3000) {
            additional += 700;
            //充值金额大于3500，需要拆分
            amount -= 3000;
            additional = getAdditionalAmount(additional,amount);
        }
        return additional;
    }
}
