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
    public partial class 变化曲线图 : Form
    {
        private SqlConnection conn;
        private SqlDataAdapter adapter;
        private SqlCommand cmd;


        public string cargoid2;
        public string staTime2;
        public string stoTime2;
        public string sensor2;



        public 变化曲线图(string cargoid, string staTime, string stoTime, string sensor)
        {
            InitializeComponent();
            cargoid2 = cargoid;
            staTime2 = staTime;
            stoTime2 = stoTime;
             sensor2 = sensor;
            CreateLine(zedGraphControl1);

        }

        /// <summary>
        /// 实例2
        /// </summary>
        public void CreateLine(ZedGraphControl zgc)
        {
            GraphPane myPane = zgc.GraphPane;

            myPane.CurveList.Clear();

            string constr = "server=.\\SQLEXPRESS;database=VDRMS;uid=sa;pwd=522300;Integrated Security = SSPI;";
            conn = new SqlConnection(constr);
            conn.Open();

           // string sqlstr = "select 湿度,温度,烟雾,采集时间 from 即时信 where cid='" + cargoid2 + "' and cast( time  as datetime) between '" + staTime2 + "' and '" + stoTime2 + "'";
            string sqlstr = "select * from 即时信 where cid='" + cargoid2 + "' and cast( time  as datetime) between '" + staTime2 + "' and '" + stoTime2 + "'and type='" + sensor2 + "'";
            cmd = new SqlCommand(sqlstr, conn);
            adapter = new SqlDataAdapter();
            adapter.SelectCommand = cmd;
            DataTable Dt = new DataTable();
            adapter.Fill(Dt);



            /*SqlCommand cmd = new SqlCommand(sql, conn);
            SqlDataAdapter adapter = new SqlDataAdapter();
            adapter.SelectCommand = cmd;
            DataSet dataset = new DataSet();
            adapter.Fill(dataset);
            dataGridView1.DataSource = dataset.Tables[0];
            conn.Close();*/

            myPane.Title.Text = ("货物" + cargoid2 + "的" + sensor2 + "变化曲线图");
            myPane.XAxis.Title.Text = "采集时间";
            myPane.YAxis.Title.Text = sensor2;


            PointPairList list1 = new PointPairList();


            if (Dt.Rows.Count <= 0)
            {
                MessageBox.Show("无历史记录！", "提示");
            }
            else
            {
                for (int i = 0; i < Dt.Rows.Count; i++)
                {
                    string Str = Dt.Rows[i]["time"].ToString().Replace(".", "/");


                    //Str = Str.Replace("-", "/");
                    //Str = Str.Replace(" ", "/");
                    string[] array = Str.Split('/');
                    string str = "";
                    for (Int32 h = 0; h <= array.Length - 2; h++)
                    {
                        str = str + array[h];
                    }
                    double x = Convert.ToDouble(str);
                    //double y = Convert.ToDouble(Dt.Rows[i][sensor2].ToString());
                    double y = Convert.ToDouble(Dt.Rows[i]["data"].ToString());

                    list1.Add(x, y);

                }
            }

            //将时间作为X轴的标尺                
            string[] xLabels = new string[Dt.Rows.Count];
            for (int i = 0; i < Dt.Rows.Count; i++)
                xLabels[i] = Dt.Rows[i]["time"].ToString().Trim();

            myPane.XAxis.Type = AxisType.Text;
            myPane.XAxis.Scale.TextLabels = xLabels;

            // 创建每个折线       
            LineItem myCurve1 = myPane.AddCurve(sensor2, list1, Color.Red);
            //myPane.YAxis.Scale.FontSpec.FontColor = Color.Red;
            //myPane.YAxis.Title.FontSpec.FontColor = Color.Red;

            myPane.XAxis.Scale.FontSpec.Angle = 90;
            zedGraphControl1.AxisChange();
            // 设置图标的颜色和渐变色                
            myPane.Chart.Fill = new Fill(Color.White, Color.FromArgb(255, 255, 166), 45.0F);
            zgc.AxisChange();
            zgc.Refresh();

        }

        private void zedGraphControl1_Load(object sender, EventArgs e)
        {

        }

        private void 变化曲线图_Load(object sender, EventArgs e)
        {

        }


    }
}