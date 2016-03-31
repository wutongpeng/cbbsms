using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using ZedGraph;
using System.Data.SqlClient;


namespace 道路运输货物信息状态检测系统
{
    public partial class 历史信息 : Form
    {

        //private SqlConnection conn;
        //private SqlDataAdapter adapter;
       // private SqlCommand cmd;
        //private DataSet dataset;

        DataGridViewPrint dataGridViewPrint;


        public 历史信息()
        {
            InitializeComponent();

        }








        //查询方法
        public void selExecuteSQL()
        {
            string constr = "server=.\\SQLEXPRESS;database=VDRMS;uid=sa;pwd=522300;Integrated Security = SSPI";
            SqlConnection conn = new SqlConnection(constr);
            conn.Open();

            string sensor = textBox1.Text.ToString();
            // string type = comboBox1.SelectedItem.ToString(); //and 传感器类型 ='" + type + "'

            string startTime = dateTimePicker1.Value.ToString("yyyy-MM-dd HH:mm:ss");
            string stoTime = dateTimePicker2.Value.ToString("yyyy-MM-dd HH:mm:ss");


            string sql = string.Format("select * from 即时信 where cast( time  as datetime) between '" + startTime + "' and '" + stoTime + "'");
            String condition = "";
            if (textBox2.Text.Trim() != "")
            {

                condition = "and type = '" + textBox2.Text + "'";
                sql += condition;
            }
             if (textBox1.Text.Trim() != "")
             {
                
                 condition = "and cid = '" + textBox1.Text + "' ";
                 sql += condition;
             }
            // if (condition != "")
              //  sql += condition;
            SqlCommand cmd = new SqlCommand(sql, conn);
            SqlDataAdapter adapter = new SqlDataAdapter();
            adapter.SelectCommand = cmd;
            DataSet dataset = new DataSet();
            adapter.Fill(dataset);
            dataGridView1.DataSource = dataset.Tables[0];
            dataGridView1.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
            conn.Close();


        }

        

        private void dataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }

        private void zedGraphControl1_Load(object sender, EventArgs e)
        {

        }

    

        private void textBox1_TextChanged(object sender, EventArgs e)
        {


        }

        private void 历史信息_Load(object sender, EventArgs e)
        {

        }

        private void button3_Click_1(object sender, EventArgs e)
        {
            //显示打印对话框
            PrintDialog MyDlg = new PrintDialog();
            MyDlg.Document = this.printDocument1;
            if (MyDlg.ShowDialog().Equals(DialogResult.OK))
            {
                //显示打印预览对话框
                dataGridViewPrint = new DataGridViewPrint(dataGridView1, printDocument1, true, true, "数据报表", new System.Drawing.Font("Tahoma", 18, FontStyle.Bold, GraphicsUnit.Point), Color.Black, true);
                PrintPreviewDialog a = new PrintPreviewDialog();
                a.Document = this.printDocument1;
                a.ShowDialog();
            }
        }

        private void button4_Click_1(object sender, EventArgs e)
        {
            //显示页面设置对话框
            PageSetupDialog MyDlg = new PageSetupDialog();
            MyDlg.Document = this.printDocument1;
            MyDlg.ShowDialog();

        }

        private void button2_Click_1(object sender, EventArgs e)
        {
            string cargoid = textBox1.Text.Trim();
            string staTime = dateTimePicker1.Value.ToString();
            string stoTime = dateTimePicker2.Value.ToString();
            string sensor = textBox2.Text.Trim();
            变化曲线图 变化曲线图 = new 变化曲线图(cargoid, staTime, stoTime,sensor);
            变化曲线图.Show();
        }

        private void printDocument1_PrintPage_1(object sender, System.Drawing.Printing.PrintPageEventArgs e)
        {
            //true表示还有数据行没有打印完,则继续打
            if (dataGridViewPrint.DrawDataGridView(e.Graphics))
            {
                //附加打印页
                e.HasMorePages = true;
            }
            else
            {
                DataGridViewPrint.PageNumber = 0;
                DataGridViewPrint.mColumnPoints.Clear();
                DataGridViewPrint.mColumPointsWidth.Clear();
            }
        }

        private void 历史信息_Load_1(object sender, EventArgs e)
        {

        }

        private void groupBox1_Enter(object sender, EventArgs e)
        {

        }

        private void button1_Click_1(object sender, EventArgs e)
        {
            selExecuteSQL();
        }

        private void comboBox1_SelectedIndexChanged(object sender, EventArgs e)
        {

        }





    }

}
