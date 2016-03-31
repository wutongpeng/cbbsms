using System;
using System.Data;
using System.Configuration;
using System.Linq;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;
using System.Web.UI.WebControls.WebParts;
using System.Xml.Linq;
using System.Data.SqlClient;
using System.Text.RegularExpressions;
using System.Collections;
using System.Collections.Generic;

namespace StockManageWebservice
{
    /// <summary>
    /// 一个操作数据库的类，所有对SQLServer的操作都写在这个类中，使用的时候实例化一个然后直接调用就可以
    /// </summary>
    public class DBOperation:IDisposable
    {
        public static SqlConnection sqlCon;  //用于连接数据库

        //将下面的引号之间的内容换成上面记录下的属性中的连接字符串
        //dfasdlfasdfsad
        private String ConServerStr = "server=.\\SQLEXPRESS;uid=sa;pwd=522300;database=VDRMS;Integrated Security = SSPI ";
        
        //默认构造函数
        public DBOperation()
        {
            if (sqlCon == null)
            {
                sqlCon = new SqlConnection();
                sqlCon.ConnectionString = ConServerStr;
                sqlCon.Open();
            }
        }
         
        //关闭/销毁函数，相当于Close()
        public void Dispose()
        {
            if (sqlCon != null)
            {
                sqlCon.Close();
                sqlCon = null;
            }
        }
        

 /// <summary>
        /// 1判断登录
        /// </summary>
        /// <param name="Cname">用户名</param>
        /// <param name="Cnum">密码</param>
        public bool getLogin(string name, string pwd)
        {
            try
            {
                string username = name;
                string userpwd = pwd;
                string sql = "select * from C where Cname='" + username + "' and Cnum='" + userpwd + "'";
                
                SqlCommand cmd = new SqlCommand(sql, sqlCon);
                SqlDataReader sread = cmd.ExecuteReader();
                
                if (sread.Read())
                {

                    return true;
                }
                else
                {
                    return false;
                }
                //return true;
            }
            catch (Exception )
            {
                return false;
            }
            finally {
                Dispose();
            }
        }

        /// <summary>
        /// 2获取所有货物的信息
        /// </summary>
        /// <returns>所有货物信息</returns>
        public List<string> selectAllCargoInfor()
        {
            List<string> list = new List<string>();

            try
            {
                string sql = "select top 1 * from C";
                SqlCommand cmd = new SqlCommand(sql,sqlCon);
                SqlDataReader reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                    //将结果集信息添加到返回向量中
                    list.Add(reader[0].ToString());
                    list.Add(reader[1].ToString());
                    list.Add(reader[2].ToString());
                   // list.Add(reader[3].ToString());
                   // list.Add(reader[4].ToString());
                   // list.Add(reader[5].ToString());
                   // list.Add(reader[6].ToString());

                }

                reader.Close();
                cmd.Dispose();

            }
            catch(Exception)
            {

            }
            return list;
        }

        /// <summary>
        /// 2.5获取所有Instant信息
        /// </summary>
        /// <returns>Instant信息</returns>
        public List<string> selectInstantInfor()
        {
            List<string> list = new List<string>();

            try
            {
                string sql = "select top 1 * from C";
                SqlCommand cmd = new SqlCommand(sql, sqlCon);
                SqlDataReader reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                    //将结果集信息添加到返回向量中
                    list.Add(reader[0].ToString());
                    list.Add(reader[1].ToString());
                    list.Add(reader[2].ToString());

                }

                reader.Close();
                cmd.Dispose();

            }
            catch (Exception)
            {

            }
            return list;
        }
/*
        /// <summary>
        /// 增加一条货物信息
        /// </summary>
        /// <param name="Cname">货物名称</param>
        /// <param name="Cnum">货物数量</param>
        public bool insertCargoInfo(string Cname, int Cnum)
        {
            try
            {
                string sql = "insert into C (Cname,Cnum) values ('" + Cname + "'," + Cnum + ")";
                SqlCommand cmd = new SqlCommand(sql, sqlCon);
                cmd.ExecuteNonQuery();
                cmd.Dispose();

                return true;
            }
            catch (Exception)
            {
                return false;
            }
        }

        /// <summary>
        /// 删除一条货物信息
        /// </summary>
        /// <param name="Cno">货物编号</param>
        public bool deleteCargoInfo(string Cno)
        {
            try
            {
                string sql = "delete from C where Cno=" + Cno;
                SqlCommand cmd = new SqlCommand(sql, sqlCon);
                cmd.ExecuteNonQuery();
                cmd.Dispose();

                return true;
            }
            catch (Exception)
            {
                return false;
            }
        }
 
*/

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////即时信息
        
       /// <summary>
       /// 5获得温度值
       /// </summary>
       public string getTemperature()
       {
           string sql = "select top 1 Tem from C;";
           SqlCommand cmd = new SqlCommand(sql, sqlCon);
           SqlDataReader sdr = cmd.ExecuteReader();
           sdr.Read();
           string tem = sdr["Tem"].ToString();          
           //int  count = Convert.ToInt32(cmd.ExecuteScalar());
           return tem;
           //sdr.Close();
       }

       /// <summary>
       /// 6获得湿度值
       /// </summary>
       public string getHumidity()
       {
           string sql = "select top 1 Hum from C;";
           SqlCommand cmd = new SqlCommand(sql, sqlCon);
           SqlDataReader sdr = cmd.ExecuteReader();
           sdr.Read();
           string hum = sdr["Hum"].ToString();
           //int  count = Convert.ToInt32(cmd.ExecuteScalar());
           return hum;
           //sdr.Close();
       }

       /// <summary>
       /// 7获得火焰浓度值
       /// </summary>
       public string getSmoke()
       {
           string sql = "select top 1 Smo from C;";
           SqlCommand cmd = new SqlCommand(sql, sqlCon);
           SqlDataReader sdr = cmd.ExecuteReader();
           sdr.Read();
           string smo = sdr["Smo"].ToString();
           //int  count = Convert.ToInt32(cmd.ExecuteScalar());
           return smo;
           //sdr.Close();
       }
       /// <summary>
       /// 8获得GPS值
       /// </summary>
       public string getGps()
       {
           string sql = "select top 1 Gps from C;";
           SqlCommand cmd = new SqlCommand(sql, sqlCon);
           SqlDataReader sdr = cmd.ExecuteReader();
           sdr.Read();
           string smo = sdr["Gps"].ToString();
           //int  count = Convert.ToInt32(cmd.ExecuteScalar());
           return smo;
           //sdr.Close();
       }
       
        
    }
}