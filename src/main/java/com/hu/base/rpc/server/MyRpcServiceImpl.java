package com.hu.base.rpc.server;

import com.hu.base.rpc.api.MyRpcService;
import com.hu.base.rpc.api.Result;
import com.hu.base.rpc.api.Send;
import lombok.extern.slf4j.Slf4j;

/**
 * 服务方rpc接口实现类
 * @author hutiantian
 * @date: 2019/1/12 12:46
 * @since 1.0.0
 */
@Slf4j
public class MyRpcServiceImpl implements MyRpcService {

    @Override
    public Result doRpc(Send send) {
        if(send!=null){
            log.debug("server受到客户端请求"+send.toString());
            //假装是业务逻辑
            if(send.getName().equals("hutiantian")&&send.getPassword().equals("123456")){
                return getSuccess();
            }
        }
        return getError();
    }

    private Result getSuccess(){
        Result res = new Result();
        res.setCode(200);
        res.setMessage("校验成功！");
        return res;
    }

    private Result getError(){
        Result res = new Result();
        res.setCode(400);
        res.setMessage("校验失败！");
        return res;
    }
}
