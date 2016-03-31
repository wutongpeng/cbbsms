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
    public partial class 货物添加 : Form
    {


        /// <summary>
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
        /// <summary>
        /// 执行非查询命令SQL命令
        /// </summary>
        /// <param name="sqlstring"></param>
        public int ExecuteSQL(string sqlstring)
        {
            
            int count= -1;
            try
            {
                open();
                SqlCommand cmd = new SqlCommand(sqlstring, sqlconnection);
                count = cmd.ExecuteNonQuery();
                if (count > 0)
                {
                    MessageBox.Show("添加成功");
                }
                else {
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
        public 货物添加()
        {
            InitializeComponent();
        }

        private void label2_Click(object sender, EventArgs e)
        {

        }
        
        //处理添加按钮
        public void button1_Click(object sender, EventArgs e)
        {
            String 货物名 = textBox1.Text.Trim();
            String 添加时间=DateTime.Now.ToString();
            ExecuteSQL("insert into 货物(货物名,添加时间) values('" + 货物名+ "','" + 添加时间 + "')");

                //MessageBox.Show("添加成功");
            

        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {

        }

    
        //处理显示框
        private void dataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
              

        }

        private  void 货物添加_Load(object sender, EventArgs e)
        {
            
            // TODO: 这行代码将数据加载到表“vDRMSDataSet.货物”中。您可以根据需要移动或删除它。
             try
            {
                this.货物TableAdapter.Fill(this.vDRMSDataSet.货物);
            }
            catch { } 

           // this.货物TableAdapter.Fill(this.vDRMSDataSet.货物); 
 

        }
        //处理查看按钮
        private void button2_Click(object sender, EventArgs e)
        {
           
        }

        
    }
}
