/**
 扩展一个test模块
 **/

layui.define(function(exports){ //提示：模块也可以依赖其它模块，如：layui.define('layer', callback);
    var obj = {
        ip_host:"http://localhost:8010",
        request: {
            pageName:"pageNo",
            limitName:"pageSize"
        },
        response: {
            dataName:"records"

        },
        hello: function(str){
            alert('Hello '+ (str||'mymod'));
        }
    };

    //输出test接口
    exports('global', obj);
});