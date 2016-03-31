using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Data.SqlClient;

namespace 道路运输货物信息状态检测系统
{
    public partial class 登陆 : Form
    {
        public 登陆()
        {
            InitializeComponent();
        }
        SqlConnection con;
        private void 登陆_Load(object sender, EventArgs e)
        {
 

        }

      

        private void textBox1_TextChanged(object sender, EventArgs e)
        {

        }

        private void textBox1_TextChanged_1(object sender, EventArgs e)
        {

        }

        private void textBox2_TextChanged(object sender, EventArgs e)
        {

        }

      

       

        private void button2_Click_1(object sender, EventArgs e)
        {
            注册 注册 = new 注册();
            this.Hide();
            注册.Show();

        }

        private void button1_Click(object sender, EventArgs e)
        {
            //字符串赋值:用户名 密码
            string username = textBox1.Text.Trim();
            string userpwd = textBox2.Text.Trim();

            //定义数据库连接语句:服务器=.(本地) 数据库名=TelephoneMS(手机管理系统)
            string consqlserver = "server=.\\SQLEXPRESS;database=VDRMS;uid=sa;pwd=522300;Integrated Security = SSPI";

            //定义SQL查询语句:用户名 密码
            string sql = "select * from users where name='" + username + "' and pwd='" + userpwd + "'";

            //定义SQL Server连接对象 打开数据库
            SqlConnection con = new SqlConnection(consqlserver);
            con.Open();

            //定义查询命令:表示对数据库执行一个SQL语句或存储过程
            SqlCommand com = new SqlCommand(sql, con);

            //执行查询:提供一种读取数据库行的方式
            SqlDataReader sread = com.ExecuteReader();

            try
            {
                //如果存在用户名和密码正确数据执行进入系统操作
                if (sread.Read())
                {
                    MessageBox.Show("登录成功");
                    this.Hide();
                    主界面 主界面 = new 主界面();
                    主界面.Show();
                }
                else
                {
                    MessageBox.Show("请输入正确的用户名和密码");
                }
            }
            catch (Exception msg)
            {
                throw new Exception(msg.ToString());  //处理异常信息
            }
            finally
            {
                con.Close();                    //关闭连接
                con.Dispose();                  //释放连接
                sread.Dispose();                //释放资源
            }
        }
    }
}
