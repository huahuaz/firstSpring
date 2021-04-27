package com.yc.starter.tx.controllers;

import com.yc.starter.tx.bean.Accounts;
import com.yc.starter.tx.bean.OpRecord;
import com.yc.starter.tx.bean.OpTypes;
import com.yc.starter.tx.service.AccountService;
import com.yc.starter.tx.vo.AccountVO;
import com.yc.starter.tx.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Slf4j
@Api(description = "银行账户操作接口", tags = {"账户操作接口", "控制层"})
public class AccountsController {

    @Autowired
    private AccountService accountService;


    @RequestMapping(value = "/openAccounts", method = {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation(value = "开户", notes = "根据金额完成开户操作，返回操作完成后新的账号和余额.")
    @ApiImplicitParams({@ApiImplicitParam(name = "money", value = "开户金额", required = true, dataType = "Double")})
    public @ResponseBody
    ResultVO openAccounts(AccountVO accountVO) {
        ResultVO rv = new ResultVO();
        log.debug("用户请求开户,存入" + accountVO.getMoney());
        try {
            Accounts a = new Accounts();
            double money = 1;
            if (accountVO.getMoney() != null || accountVO.getMoney() > 0) {
                money = accountVO.getMoney();
            }
            Integer id = accountService.openAccount(a, money);
            a.setAccountId(id);
            a.setBalance(money);
            rv.setCode(1);
            rv.setData(a);
        } catch (Exception e) {
            e.printStackTrace();
            rv.setCode(0);
            rv.setMsg(e.getMessage());
        }
        return rv;
    }

    @RequestMapping(value = "/deposite", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "存款", notes = "根据账号，存款金额发出存款操作，返回操作完成后新的余额.")
    @ApiImplicitParams({@ApiImplicitParam(name = "accountId", value = "存款账号", required = true, dataType = "Int"),
            @ApiImplicitParam(name = "money", value = "存款金额", required = true, dataType = "Double")})
    public @ResponseBody
    ResultVO deposite(AccountVO accountVO) {
        ResultVO rv = new ResultVO();
        log.debug(accountVO.getAccountId() + "用户请求存钱，存入" + accountVO.getMoney());
        double money = 0;
        try {
            Accounts a = new Accounts();
            if (accountVO.getAccountId() != null || accountVO.getAccountId() > 0) {
                if (accountVO.getMoney() != null || accountVO.getMoney() > 0) {
                    a.setAccountId(accountVO.getAccountId());
                    money = accountVO.getMoney();
                }
            }
            a = accountService.deposite(a, money, OpTypes.deposite.getName(), null);
            rv.setCode(1);
            rv.setData(a);
        } catch (Exception e) {
            e.printStackTrace();
            rv.setCode(0);
            rv.setMsg(e.getMessage());
        }
        return rv;
    }

    @RequestMapping(value = "/withdraw", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "取款", notes = "根据账号，取款金额发出取款操作，返回操作完成后新的余额.")
    @ApiImplicitParams({@ApiImplicitParam(name = "accountId", value = "取款账号", required = true, dataType = "Int"),
            @ApiImplicitParam(name = "money", value = "取款金额", required = true, dataType = "Double")})
    public @ResponseBody
    ResultVO withdraw(AccountVO accountVO) {
        ResultVO rv = new ResultVO();
        log.debug(accountVO.getAccountId() + "用户请求取钱，取出" + accountVO.getMoney());
        double money = 0;
        try {
            Accounts a = new Accounts();
            if (accountVO.getAccountId() != null || accountVO.getAccountId() > 0) {
                if (accountVO.getMoney() != null || accountVO.getMoney() > 0) {
                    a.setAccountId(accountVO.getAccountId());
                    money = accountVO.getMoney();
                }
            }
            a = accountService.withdraw(a, money, OpTypes.withdraw.getName(), null);
            rv.setCode(1);
            rv.setData(a);
        } catch (Exception e) {
            e.printStackTrace();
            rv.setCode(0);
            rv.setMsg(e.getMessage());
        }
        return rv;
    }

    /*@RequestMapping(value = "transfer", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    ResultVO transfer(AccountVO accountVO) {
        ResultVO rv = new ResultVO();
        log.debug(accountVO.getAccountId() + "用户请求转账" + accountVO.getMoney() + "给用户" + accountVO.getInAccountId());
        double money = 0;
        try {
            Accounts out = new Accounts();
            Accounts in = new Accounts();
            if (accountVO.getAccountId() != null || accountVO.getAccountId() > 0) {
                if (accountVO.getInAccountId() != null || accountVO.getInAccountId() > 0) {
                    if (accountVO.getMoney() != null || accountVO.getMoney() > 0) {
                        out.setAccountId(accountVO.getAccountId());
                        in.setAccountId(accountVO.getInAccountId());
                        money = accountVO.getMoney();
                    }
                }
            }
            Accounts a = accountService.transfer(in, out, money);
            rv.setCode(1);
            rv.setData(a);
        } catch (Exception e) {
            e.printStackTrace();
            rv.setCode(0);
            rv.setMsg(e.getMessage());
        }
        return rv;
    }*/

    @RequestMapping(value = "/transfer", method = RequestMethod.POST)
    @ApiOperation(value = "转账", notes = "根据账户编号及金额, 对方接收账号来完成转账操作，注意此时的金额表示要取的金额")
    @ApiImplicitParams({@ApiImplicitParam(name = "accountId", value = "自己账户编号", required = true, dataType = "Int"),
            @ApiImplicitParam(name = "money", value = "转账金额", required = true, dataType = "Double"),
            @ApiImplicitParam(name = "inAccountId", value = "对方接收账号", required = true, dataType = "Int")})
    public @ResponseBody
    ResultVO<Accounts> transfer(AccountVO accountVO) {
        Accounts inAccount = new Accounts();
        inAccount.setAccountId(accountVO.getInAccountId());
        Accounts outAccount = new Accounts();
        outAccount.setAccountId(accountVO.getAccountId());
        ResultVO<Accounts> rv = new ResultVO();
        try {
            Accounts a = accountService.transfer(inAccount, outAccount, accountVO.getMoney());
            rv.setCode(1);
            rv.setData(a);
        } catch (Exception ex) {
            ex.printStackTrace();
            rv.setCode(0);
            rv.setMsg(ex.getMessage());
        }
        return rv;
    }


    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    @ApiOperation(value = "查询", notes = "根据账户来完成查询操作，返回账号和金额")
    @ApiImplicitParams({@ApiImplicitParam(name = "accountId", value = "账户编号", required = true, dataType = "Int")})
    public @ResponseBody
    ResultVO<Accounts> findById(AccountVO accountVO) {
        Accounts id = new Accounts();
        id.setAccountId(accountVO.getAccountId());
        ResultVO rv = new ResultVO();
        try {
            List<OpRecord> a = accountService.findById(id);
            rv.setCode(1);
            rv.setData(a);
        } catch (Exception e) {
            e.printStackTrace();
            rv.setCode(0);
            rv.setMsg(e.getMessage());
        }
        return rv;
    }
}
