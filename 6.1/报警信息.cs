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
    public partial class 报警信息 : Form
    {
        
       
        public 报警信息()
        {
            InitializeComponent();
        }
      


        private void label2_Click(object sender, EventArgs e)
        {

        }

        private void comboBox1_SelectedIndexChanged(object sender, EventArgs e)
        {


        }

       

        private void button1_Click(object sender, EventArgs e)
        {
            selExecuteSQL();

        }
       
        //查询方法
        public void selExecuteSQL()
        {
            string constr = "server=.\\SQLEXPRESS;database=VDRMS;uid=sa;pwd=123;Integrated Security = SSPI";
          SqlConnection conn = new SqlConnection(constr);
            conn.Open();

            string sensor = textBox1.Text.ToString();
             string startTime = dateTimePicker1.Value.ToString("yyyy-MM-dd HH:mm:ss");
             string stoTime = dateTimePicker2.Value.ToString("yyyy-MM-dd HH:mm:ss");
             string levels = textBox3.Text.ToString();


             string sql = string.Format("select * from Warninfo where cast( 报警时间  as datetime) between '" + startTime + "' and '" + stoTime + "'");
             String condition = "";
             if (textBox2.Text.Trim() != "")
             {

                 condition = "and 传感器类型 = '" + textBox2.Text + "'";
                 sql += condition;
             }
             if (textBox1.Text.Trim() != "")
             {

                 condition = "and 货物ID = '" + textBox1.Text + "' ";
                 sql += condition;
             }
             if (textBox3.Text.Trim() != "")
             {

                 condition = "and 报警级别 = '" + textBox3.Text + "' ";
                 sql += condition;
             }




             SqlCommand cmd = new SqlCommand(sql, conn);
            SqlDataAdapter adapter= new SqlDataAdapter();
             adapter.SelectCommand = cmd;
            DataSet dataset= new DataSet();
             adapter.Fill(dataset);
             dataGridView1.DataSource = dataset.Tables[0];
             
             
             conn.Close();

             if (dataGridView1.Rows.Count > 0)
             {


                 foreach (DataGridViewRow row in dataGridView1.Rows)
                 {
                     //if (row.Cells[6].Value == null)
                     //{
                     //    row.DefaultCellStyle.ForeColor = Color.Red;
                     //    continue;
                     //}
                     
                     string str = row.Cells[6].Value.ToString().Trim();
                     if (str == "偏高")
                     {
                        row.DefaultCellStyle.ForeColor = Color.Orange;
                     }
                     if (str == "危险")
                     {
                         row.DefaultCellStyle.ForeColor = Color.Red;
                     }
                     if (str == "偏低")
                     {
                         row.DefaultCellStyle.ForeColor = Color.Olive;
                     }
                     if (str == "紧急")
                     {
                         row.DefaultCellStyle.ForeColor = Color.Blue;
                     }
                 }

             }
        }

        
      
        private void dataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

           
        }

        private void dateTimePicker1_ValueChanged(object sender, EventArgs e)
        {

        }

        private void dateTimePicker2_ValueChanged(object sender, EventArgs e)
        {

        }

        private void 报警信息_Load(object sender, EventArgs e)
        {

        }

        private void groupBox1_Enter(object sender, EventArgs e)
        {

        }

        private void textBox3_TextChanged(object sender, EventArgs e)
        {

        }
        
    }
}
