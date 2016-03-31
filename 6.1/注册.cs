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
    public partial class 注册 : Form
    {

        public 注册()
        {
            InitializeComponent();
        }
        SqlConnection sqlconnection;
        private void 注册_Load(object sender, EventArgs e)
        {

        }


        public int ExecuteSQL(string sqlstring)
        {
            int count = -1;
            try
            {
                open();
                SqlCommand cmd = new SqlCommand(sqlstring, sqlconnection);
                count = cmd.ExecuteNonQuery();

                if (count > 0)
                {
                    MessageBox.Show("注册成功，请登录！");


                }
                else
                {
                    MessageBox.Show("服务器忙，请稍后再试");
                }
            }
            catch (Exception ex)
            {

                Console.WriteLine(ex);
            }
            finally
            {
                close();
            }
            return count;

        }

        /// <summary>
        // / 打开数据库
        /// </summary>
        public void open()
        {
            try
            {

                string connstring = "server=.\\SQLEXPRESS;database=VDRMS;uid=sa;pwd=522300;Integrated Security = SSPI";
                sqlconnection = new SqlConnection(connstring);
                sqlconnection.Open();
            }
            catch (Exception ex)
            {

                Console.WriteLine(ex);
            }
        }
        /// <summary>
        /// 关闭数据库
        /// </summary>
        public void close()
        {
            try
            {
                //释放所使用的资源
                sqlconnection.Dispose();
                //关闭连接
                sqlconnection.Close();
                sqlconnection = null;
            }
            catch (Exception ex)
            {

                Console.WriteLine(ex);
            }
        }


        private void button2_Click(object sender, EventArgs e)
        {
            登陆 登陆 = new 登陆();
            this.Hide();
            登陆.Show();

        }

        private void button1_Click(object sender, EventArgs e)
        {

            string username = textBox1.Text.Trim();
            string userpwd = textBox2.Text.Trim();
            string userpwdagin = textBox3.Text.Trim();
            string consqlserver = "server=.\\SQLEXPRESS;database=VDRMS;uid=sa;pwd=522300;Integrated Security = SSPI";
            sqlconnection = new SqlConnection(consqlserver);
            sqlconnection.Open();

            string sql = "select * from users where name='" + username + "'";

            SqlCommand com = new SqlCommand(sql, sqlconnection);
            SqlDataReader sread = com.ExecuteReader();


           
                if (userpwd != userpwdagin)
                {
                    MessageBox.Show("两次输入的密码不一致");

                }
                else
                {
                    if (sread.Read())
                    {
                        MessageBox.Show("用户名已存在");

                    }
                    else
                    {
                        string sqlregin = "insert into users (name,pwd) values('" + username + "','" + userpwd + "')";
                        ExecuteSQL(sqlregin);
                    }

                }
            }
           
           


        }

    }
       
    

