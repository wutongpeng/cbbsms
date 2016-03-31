using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;

namespace StockManageWebservice
{
    /// <summary>
    /// Service1 的摘要说明
    /// </summary>
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // 若要允许使用 ASP.NET AJAX 从脚本中调用此 Web 服务，请取消对下行的注释。
    // [System.Web.Script.Services.ScriptService]
    public class Service1 : System.Web.Services.WebService
    {
        DBOperation dbOperation = new DBOperation();

        [WebMethod]
        public string HelloWorld()
        {
            return "Hello World";

        }
        
        //接口1
        [WebMethod(Description = "判断登录")]
        public bool getLogin(string name, string pwd)
        {
            return dbOperation.getLogin(name, pwd);
        }
       
        //接口2
        [WebMethod(Description = "获取所有货物的信息")]
        public string[] selectAllCargoInfor()
        {
            return dbOperation.selectAllCargoInfor().ToArray();
        }
   
   

 ////////////////////////////////////////////////////////////////////////////////////////////////////////////即时信息
        //接口5
        [WebMethod(Description = "获得温度值")]
        public string getTemperature()
        {
            return dbOperation.getTemperature();
        }
        //接口6
        [WebMethod(Description = "获得湿度值")]
        public string getHumidity()
        {
            return dbOperation.getHumidity();
        }
       
       
        //接口7
        [WebMethod(Description = "获得烟雾浓度值")]
        public string getSmoke()
        {
            return dbOperation.getSmoke();
        }
        //接口8
        [WebMethod(Description = "获得gps地理位置")]
        public string getGps()
        {
            return dbOperation.getGps();
        }

       /*

        [WebMethod(Description = "增加一条货物信息")]
        public bool insertCargoInfo(string Cname, int Cnum)
        {
            return dbOperation.insertCargoInfo(Cname, Cnum);
        }

        [WebMethod(Description = "删除一条货物信息")]
        public bool deleteCargoInfo(string Cno)
        {
            return dbOperation.deleteCargoInfo(Cno);
        }
        */
    }
}