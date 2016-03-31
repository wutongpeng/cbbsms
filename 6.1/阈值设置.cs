using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Data.SqlClient;
using System.Net.Sockets;
using System.Net;
using System.Threading;

namespace 道路运输货物信息状态检测系统
{




    public partial class 阈值设置 : Form
    {
        public 阈值设置()
        {
            InitializeComponent();
        }



        /// 数据库连接
        /// </summary>
        SqlConnection sqlconnection;
        /// <summary>
        /// 

        /// <summary>
        // / 打开数据库
        /// </summary>
        public void open()
        {
            try
            {

                string connstring = "server=.\\SQLEXPRESS;uid=sa;pwd=522300;database=VDRMS;Integrated Security = SSPI ";
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


        //判断是否存入数据库是否存在
        public int creExecuteSQL(string sqlstring)
        {
            int count = -1;
            try
            {
                open();
                SqlCommand cmd = new SqlCommand(sqlstring, sqlconnection);
                count = cmd.ExecuteNonQuery();

                if (count > 0)
                {
                    MessageBox.Show("添加成功");
                    string id = textBox1.Text.Trim();

                }
                else
                {
                    MessageBox.Show("添加失败");
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

        //创建表
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
                    MessageBox.Show("创建表成功");
                }
                else
                {
                    MessageBox.Show("创建表失败");
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

        //crete添加方法

        public void Islive()
        {

            //定义货物信息
            string id = textBox1.Text.Trim();
            //string name = textBox2.Text.Trim();

           
            //温度
            string tb = tempmax.Text.Trim();
            string th = temph.Text.Trim();
            string tl = templ.Text.Trim();
            string ts = tempmin.Text.Trim();

            //湿度
            string hb = humimax.Text.Trim();
            string hh = humih.Text.Trim();
            string hl = humil.Text.Trim();
            string hs = humimin.Text.Trim();
            //烟雾
            string sb = smokemax.Text.Trim();
            string sh = smokeh.Text.Trim();


            string consqlserver = "server=.\\SQLEXPRESS;uid=sa;pwd=522300;database=VDRMS;Integrated Security = SSPI ";
            string sql = "select * from yuzhi where cid='" + id + "'";

            SqlConnection con = new SqlConnection(consqlserver);
            con.Open();
            SqlCommand com = new SqlCommand(sql, con);
            SqlDataReader sread = com.ExecuteReader();

            try
            {
                if (sread.Read())
                {
                    MessageBox.Show("货物Id已存在");




                }
                else
                {
                    creExecuteSQL("insert into yuzhi(cid,tempmax,temph,templ,tempmin,humimax,humih,humil,humimin,smokemax,smokeh,time) values('" + id + "','" + tb + "','" + th + "','" + tl + "','" + ts + "','" + hb + "','" + hh + "','" + hl + "','" + hs + "','" + sb + "','" + sh + "','" + DateTime.Now.ToString() + "')");
                    selExecuteSQL();

                    //ExecuteSQL("create table " + id + "(xuhao int primary key ,temp nchar(10),humi nchar(10),zhendong nchar(10),fire nchar(10),smoke nchar(10),address nchar(10),time nchar(20))");

                }
            }
            catch (Exception msg)
            {
                throw new Exception(msg.ToString());
            }
            finally
            {
                con.Close();
                con.Dispose();
                sread.Dispose();
            }

        }

        //delete方法
        public int delExecuteSQL(string sqlstring)
        {
            int count = -1;
            try
            {
                open();
                SqlCommand cmd = new SqlCommand(sqlstring, sqlconnection);
                count = cmd.ExecuteNonQuery();

                if (count > 0)
                {
                    MessageBox.Show("删除成功");
                }
                else
                {
                    MessageBox.Show("删除失败");
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

        //update方法
        public int updExecuteSQL(string sqlstring)
        {
            int count = -1;
            try
            {
                open();
                SqlCommand cmd = new SqlCommand(sqlstring, sqlconnection);
                count = cmd.ExecuteNonQuery();

                if (count > 0)
                {
                    MessageBox.Show("修改成功");
                }
                else
                {
                    MessageBox.Show("修改失败");
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

        //Select方法
        public void selExecuteSQL()
        {

            try
            {
                string connstring = "server=.\\SQLEXPRESS;uid=sa;pwd=522300;database=VDRMS;Integrated Security = SSPI ";
                sqlconnection = new SqlConnection(connstring);

                sqlconnection.Open();
                string id = textBox1.Text.Trim();
                string sql = "select * from yuzhi where yuzhi.cid=" + "'" + id + "'";
                SqlCommand cmd = new SqlCommand(sql, sqlconnection);

                SqlDataAdapter da = new SqlDataAdapter();
                da.SelectCommand = cmd;
                DataSet ds = new DataSet();
                da.Fill(ds);
                dataGridView1.DataSource = ds.Tables[0];

            }
            catch (Exception ex)
            {

                Console.WriteLine(ex);
            }
            finally
            {
                close();
            }


        }

        private void 阈值设置_Load(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            Islive();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            int i = Convert.ToInt32(dataGridView1.CurrentRow.Cells["cid"].Value);
            string sql = "delete  from yuzhi where yuzhi.cid=" + i;
            delExecuteSQL(sql);
            selExecuteSQL();
        }

        private void button4_Click(object sender, EventArgs e)
        {
            string id = textBox1.Text.Trim();
            //温度
            string tb = tempmax.Text.Trim();
            string th = temph.Text.Trim();
            string tl = templ.Text.Trim();
            string ts = tempmin.Text.Trim();

            //湿度
            string hb = humimax.Text.Trim();
            string hh = humih.Text.Trim();
            string hl = humil.Text.Trim();
            string hs = humimin.Text.Trim();
            //烟雾
            string sb = smokemax.Text.Trim();
            string sh = smokeh.Text.Trim();
            //string name = textBox2.Text.Trim();
            updExecuteSQL("update  yuzhi set  tempmax='" + tb + "',temph='" + th + "',templ='" + tl + "',tempmin='" + ts + "',humimax='" + hb + "',humih='" + hh + "',humil='" + hl + "',humimin='" + hs + "',smokemax='" + sb + "',smokeh='" + sh + "',time='" + DateTime.Now.ToString() + "' where yuzhi.cid='" + id + "' ");
            selExecuteSQL();
        }

        private void button3_Click(object sender, EventArgs e)
        {
            selExecuteSQL();
        }

        private void dataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
            dataGridView1.SelectionMode = DataGridViewSelectionMode.FullRowSelect;
        }

        

       
    }
}
