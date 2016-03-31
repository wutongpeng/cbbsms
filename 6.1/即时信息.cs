using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using Sensor_Microsec;
using System.Data.SqlClient;
using System.Threading;
using System.Collections;
using System.Timers;
using System.Data.OleDb;


namespace 道路运输货物信息状态检测系统
{
    public partial class 即时信息 : Form
    {

        int i = 1;
        public 即时信息()
        {
            InitializeComponent();
            //axWindowsMediaPlayer1.settings.setMode("loop", true);
            //axWindowsMediaPlayer2.settings.setMode("loop", true);
            //axWindowsMediaPlayer3.settings.setMode("loop", true);
            //axWindowsMediaPlayer4.settings.setMode("loop", true);
            //axWindowsMediaPlayer5.settings.setMode("loop", true);
        }

        SensorMicrosec sensor = new SensorMicrosec();
        /// <summary>
        /// 网关IP
        /// </summary>
        string strIP = "192.168.1.53";
        /// <summary>
        /// Port
        /// </summary>
        string strPort = "51000";
        /// <summary>
        /// 采集温度操作
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        /// <summary>
        /// 数据库连接
        /// </summary>
        SqlConnection sqlconnection;
        /// <summary>

        
        private void 即时信息_Load(object sender, EventArgs e)
        {

        }

        System.Timers.Timer timerClock = new System.Timers.Timer();
        private void button1_Click_1(object sender, EventArgs e)
        {
            Control.CheckForIllegalCrossThreadCalls = false;
            
            MessageBox.Show("你选择的频率为:" + comboBox2.SelectedItem.ToString() + "ms");
            timerClock.Interval = Convert.ToInt32(comboBox2.SelectedItem.ToString());
            timerClock.Enabled = true;
            timerClock.Elapsed += new ElapsedEventHandler(OnTimer);
        }
      
        


        public void OnTimer(Object source, ElapsedEventArgs e)
        {
            string id = textBox2.Text.Trim();
            string constr = "server=.\\SQLEXPRESS;database=VDRMS;uid=sa;pwd=522300;Integrated Security = SSPI";
            SqlConnection conn = new SqlConnection(constr);
            conn.Open();
            string sqlstr = string.Format(@"select * from yuzhi where cid={0}
                                          select * from wangguan  where cid={0}",id);
            

           // string sqlstr ="select * from yuzhi where cid ='" + id + "'";
            SqlCommand cmd = new SqlCommand(sqlstr, conn);
            SqlDataAdapter adapter = new SqlDataAdapter();
            
            adapter.SelectCommand = cmd;

            DataSet ds = new DataSet();
           
            adapter.Fill(ds,"tab");
           // adapter.Fill(ds);
            conn.Close();
            //wanguan


            var Dt = ds.Tables[0];
            var dt1 = ds.Tables[1];
           
           
            string aa = textBox1.Text.Trim();
           
           // string tempmax = Dt.Rows[0]["tempmax"].ToString();
            //string temph = Dt.Rows[0][1].ToString();
            //string templ = Dt.Rows[0][2].ToString();
            //string tempmin =Dt.Rows[0][3].ToString();

            string humimax = Dt.Rows[0]["humimax"].ToString();
            string humih = Dt.Rows[0]["humih"].ToString();
            string humil = Dt.Rows[0]["humil"].ToString();
            string humimin = Dt.Rows[0]["humimin"].ToString();

            //string vibmax = Dt.Rows[0]["vibmax"].ToString();
           // string vibh = Dt.Rows[0]["vibh"].ToString();

            //string firemax = Dt.Rows[0]["firemax"].ToString();
           // string fireh = Dt.Rows[0]["fireh"].ToString();

            string smokemax = Dt.Rows[0]["smokemax"].ToString();
            string smokeh = Dt.Rows[0]["smokeh"].ToString();

           //string strIP = dt1.Rows[0]["ip"].ToString();
            //string strIP = "192.168.1.53";
           // string strPort = dt1.Rows[0]["port"].ToString();

            //湿度 
           // string playe = axWindowsMediaPlayer1.playState.ToString();
          //  this.textBox3.Text = playe;
            string strSensorID = "2";
            string[] strResult = sensor.getHumidity(strIP, strPort, strSensorID, "", "", "");
            this.humi.Text = strResult[1] + "%";
            this.richTextBox1.AppendText("湿度状态：" + strResult[0] + "信息：" + strResult[1] + "\n");
            ExecuteSQL("insert into 即时信(cid,type,data,time) values('" + id + "','湿度','" + strResult[1] + "','" + DateTime.Now.ToString() + "')");
           //正常
            if (Convert.ToInt32(strResult[1]) > Convert.ToInt32(humil) && Convert.ToInt32(strResult[1]) < Convert.ToInt32(humih))
            {
                label1.ForeColor = Color.Black;
                this.humi.SelectAll();
                humi.SelectionColor = Color.Black;
              //  ExecuteSQL("insert into Warninfo (报警时间,货物ID,传感器类型,数据序号,报警数据,报警级别) values('" + DateTime.Now.ToString() + "','" + id + "','湿度','" + i + "','" + strResult[1] + "','危险')");
            //   if(playe=="3"){
             //   axWindowsMediaPlayer1.Ctlcontrols.stop();
          // }
            }
                //偏高
            else if (Convert.ToInt32(strResult[1]) >= Convert.ToInt32(humih) && Convert.ToInt32(strResult[1]) < Convert.ToInt32(humimax))
            {
                label1.ForeColor = Color.Orange;
                this.humi.SelectAll();
                humi.SelectionColor = Color.Orange;
                ExecuteSQL("insert into Warninfo (报警时间,货物ID,传感器类型,数据序号,报警数据,报警级别) values('" + DateTime.Now.ToString() + "','" + id + "','湿度','" + i + "','" + strResult[1] + "','偏高')");
              //  if (playe == "3")
              //  {
               //     axWindowsMediaPlayer1.Ctlcontrols.stop();
               // }
            }
                //偏低
            else if (Convert.ToInt32(strResult[1]) > Convert.ToInt32(humimin) && Convert.ToInt32(strResult[1]) <= Convert.ToInt32(humil))
            {
                label1.ForeColor = Color.Olive;
                this.humi.SelectAll();
                humi.SelectionColor = Color.Olive;
                ExecuteSQL("insert into Warninfo (报警时间,货物ID,传感器类型,数据序号,报警数据,报警级别) values('" + DateTime.Now.ToString() + "','" + id + "','湿度','" + i + "','" + strResult[1] + "','偏低')");
             //   if (playe == "3")
              //  {
             //       axWindowsMediaPlayer1.Ctlcontrols.stop();
               // } 
            }

                //危险
            else if (Convert.ToInt32(strResult[1]) >= Convert.ToInt32(humimax)) {
                label1.ForeColor = Color.Red;
                this.humi.SelectAll();
                humi.SelectionColor = Color.Red;
                ExecuteSQL("insert into Warninfo (报警时间,货物ID,传感器类型,数据序号,报警数据,报警级别) values('" + DateTime.Now.ToString() + "','" + id + "','湿度','" + i + "','" + strResult[1] + "','危险')");
                //axWindowsMediaPlayer1.Invoke(new Action(() => {
               /* axWindowsMediaPlayer1.BeginInit();
                this.Controls.Add(this.axWindowsMediaPlayer1);
                this.axWindowsMediaPlayer1.EndInit();
                    //axWindowsMediaPlayer1 = new AxWMPLib.AxWindowsMediaPlayer();
                axWindowsMediaPlayer1.Ctlcontrols.play();
                axWindowsMediaPlayer1.URL = @"C:\Users\Administrator\Desktop\6.0\music\湿度.wav";*/
                
                
            
            }
                //紧急
            else if (Convert.ToInt32(strResult[1]) <= Convert.ToInt32(humimax)) 
            {
                label1.ForeColor = Color.Blue;
                this.humi.SelectAll();
                humi.SelectionColor = Color.Blue;
                ExecuteSQL("insert into Warninfo (报警时间,货物ID,传感器类型,数据序号,报警数据,报警级别) values('" + DateTime.Now.ToString() + "','" + id + "','湿度','" + i + "','" + strResult[1] + "','紧急')");
                //axWindowsMediaPlayer1.Invoke(new Action(() =>
                //{
                    //axWindowsMediaPlayer1 = new AxWMPLib.AxWindowsMediaPlayer();
               /* this.axWindowsMediaPlayer1.BeginInit();
                this.Controls.Add(this.axWindowsMediaPlayer1);
                this.axWindowsMediaPlayer1.EndInit();
                    axWindowsMediaPlayer1.Ctlcontrols.play();
                    axWindowsMediaPlayer1.URL = @"C:\Users\Administrator\Desktop\6.0\music\湿度.wav";*/
                //}));
            }
           
            //继电器
            string strSensorID7 = "102";
            string[] strResult7 = sensor.getOrSetSwitch(strIP, strPort, strSensorID7, "", "", "", "2", "0");
            //烟雾传感器
           // string play = axWindowsMediaPlayer2.playState.ToString();
            string strSensorID1 = "8";
            string[] strResult1 = sensor.getSmoke(strIP, strPort, strSensorID1, "", "", "");
            this.smoke.Text = strResult1[1] + "PPM";
            this.richTextBox1.AppendText("烟雾状态：" + strResult1[0] + "信息：" + strResult1[1] + "\n");
            ExecuteSQL("insert into 即时信(cid,type,data,time) values('" + id + "','烟雾','" + strResult1[1] + "','" + DateTime.Now.ToString() + "')");
            //正常
            if (Convert.ToInt32(strResult1[1]) < Convert.ToInt32(smokeh) )
            {
                label6.ForeColor = Color.Black;
                this.smoke.SelectAll();
                smoke.SelectionColor = Color.Black;
               
                    string[] str = sensor.getOrSetSwitch(strIP, strPort, strSensorID7, "", "", "", "3", "0");
                 //   if (play == "wmppsStopped")
                //    {    
               // axWindowsMediaPlayer2.Ctlcontrols.stop();}
                //正转或停时反转
                /*  if (strResult8[1].Substring(0, 1) == "1" || strResult8[1].Substring(0, 1) == "3")
                  {
                      this.sensor.getOrSetStepMotor(strIP, strPort, "2", "", "", "", "3", "2", 500);
                  }*/
            }
              //偏高
              else if (Convert.ToInt32(strResult1[1]) >= Convert.ToInt32(smokeh) && Convert.ToInt32(strResult1[1]) < Convert.ToInt32(smokemax))
              {
                  label6.ForeColor = Color.Orange;
                  this.smoke.SelectAll();
                  smoke.SelectionColor = Color.Orange;
                
                  ExecuteSQL("insert into Warninfo (报警时间,货物ID,传感器类型,数据序号,报警数据,报警级别) values('" + DateTime.Now.ToString() + "','" + id + "','烟雾','" + i + "','" + strResult[1] + "','偏高')");
               /*   if (play == "wmppsStopped")
                  {
                      axWindowsMediaPlayer2.Ctlcontrols.stop();
                  }*/
                  //正转或停时反转
                  /*  if (strResult8[1].Substring(0, 1) == "1" || strResult8[1].Substring(0, 1) == "3")
                    {
                        this.sensor.getOrSetStepMotor(strIP, strPort, "2", "", "", "", "3", "2", 500);
                    }*/
                
                        string[] str = sensor.getOrSetSwitch(strIP, strPort, strSensorID7, "", "", "", "3", "1");
                 
                }
                //危险
                else if (Convert.ToInt32(strResult1[1]) >= Convert.ToInt32(smokemax))
                {
                    label6.ForeColor = Color.Red;
                    this.smoke.SelectAll();
                    smoke.SelectionColor = Color.Red;
                    ExecuteSQL("insert into Warninfo (报警时间,货物ID,传感器类型,数据序号,报警数据,报警级别) values('" + DateTime.Now.ToString() + "','" + id + "','烟雾','" + i + "','" + strResult[1] + "','危险')");
                   //反转或停时正转
                  /*  if (strResult8[1].Substring(0, 1) == "2" || strResult8[1].Substring(0, 1) == "3")
                    {
                        this.sensor.getOrSetStepMotor(strIP, strPort, "2", "", "", "", "3", "1", 500);
                    }
                    */

                    string[] str = sensor.getOrSetSwitch(strIP, strPort, strSensorID7, "", "", "", "3", "1");
                    //axWindowsMediaPlayer2.Invoke(new Action(() => { 
                    //new AxWMPLib.AxWindowsMediaPlayer();
                /*    this.axWindowsMediaPlayer2.BeginInit();
                    this.Controls.Add(axWindowsMediaPlayer2);
                    this.axWindowsMediaPlayer2.EndInit();
                    axWindowsMediaPlayer2.Ctlcontrols.play();
                    axWindowsMediaPlayer2.URL = @"C:\Users\Administrator\Desktop\6.0\music\湿度.wav";*/
                    //})); 
                
            }


            //火焰
          // string pla = axWindowsMediaPlayer3.playState.ToString();
            string fires = fire.Text.Trim();
            string strSensorID2 = "156";
            string[] strResult2 = sensor.getFire(strIP, strPort, strSensorID2, "", "", "");
            //this.richTextBox4.Text = strResult2[1] + "%";
            this.richTextBox1.AppendText("火焰状态：" + strResult2[0] + "信息：" + strResult2[1] + "\n");
            ExecuteSQL("insert into 即时信(cid,type,data,time) values('" + id + "','火焰','" + fires + "','" + DateTime.Now.ToString() + "')");
            if (strResult2[1] == "3")
            {
                this.fire.Text = "未发现火焰";
                this.richTextBox1.AppendText("火焰状态：未发现火焰 " + "信息：" + strResult2[1] + "\r\n");
              /*  if (pla == "wmppsStopped")
                {
                axWindowsMediaPlayer3.Ctlcontrols.stop();}*/
            }
            else if (strResult2[1] == "1")
            {
                this.fire.Text = "发现火焰";
               /* this.axWindowsMediaPlayer3.BeginInit();
                this.Controls.Add(axWindowsMediaPlayer3);
                this.axWindowsMediaPlayer3.EndInit();
                axWindowsMediaPlayer3.Ctlcontrols.play();
                axWindowsMediaPlayer3.URL = @"C:\Users\Administrator\Desktop\6.0\music\火焰.wav";*/
                ExecuteSQL("insert into Warninfo (报警时间,货物ID,传感器类型,数据序号,报警数据,报警级别) values('" + DateTime.Now.ToString() + "','" + id + "','火焰','" + i + "','" + fires + "','危险')");
               
            }
            //震动传感器
           // string pl = axWindowsMediaPlayer4.playState.ToString();
            string vibs = richTextBox5.Text.Trim();
            string strSensorID3 = "157";
            string[] strResult3 = sensor.getVibration(strIP, strPort, strSensorID3, "", "", "");
           // this.richTextBox5.Text = strResult3[1] + "%";
            this.richTextBox1.AppendText("震动状态：" + strResult3[0] + "信息：" + strResult3[1] + "\n");
            ExecuteSQL("insert into 即时信(cid,type,data,time) values('" + id + "','震动','" + vibs + "','" + DateTime.Now.ToString() + "')");
            if (strResult3[1] == "3")
            {
                this.richTextBox5.Text = "未震动";
               // this.richTextBox1.AppendText("火焰状态：未发现火焰 " + "信息：" + strResult2[1] + "\r\n");
               
            }
            else if (strResult3[1] == "1")
            {
                this.richTextBox5.Text = "有震动";
                ExecuteSQL("insert into Warninfo (报警时间,货物ID,传感器类型,数据序号,报警数据,报警级别) values('" + DateTime.Now.ToString() + "','" + id + "','震动','" + i + "','" + vibs + "','危险')");
            
            }
            //步进电机
            string[] strResult8 = sensor.getOrSetStepMotor(strIP, strPort, "104", "", "", "", "2", "0", 500);
            //温度
            //string p = axWindowsMediaPlayer5.playState.ToString();
            string tmax = Dt.Rows[0]["tempmax"].ToString();
            string th = Dt.Rows[0]["temph"].ToString();
            string tl = Dt.Rows[0]["templ"].ToString();
            string tmin = Dt.Rows[0]["tempmin"].ToString();
            string strSensorID4 = "1";
            string[] strResult4 = sensor.getTemperature(strIP, strPort, strSensorID4, "", "", "");
            this.temp.Text = strResult4[1] + "%";
            this.richTextBox1.AppendText("温度状态：" + strResult4[0] + "信息：" + strResult4[1] + "\n");
            ExecuteSQL("insert into 即时信(cid,type,data,time) values('" + id + "','温度','" + strResult4[1] + "','" + DateTime.Now.ToString() + "')");
            //正常
            if (Convert.ToInt32(strResult4[1]) > Convert.ToInt32(tl) && Convert.ToInt32(strResult4[1]) < Convert.ToInt32(th))
            {
                label3.ForeColor = Color.Black;
                this.temp.SelectAll();
                temp.SelectionColor = Color.Black;

                string[] strR = sensor.getOrSetStepMotor(strIP, strPort, "104", "", "", "", "3", "0", 0);

            }
            //偏高
            else if (Convert.ToInt32(strResult4[1]) >= Convert.ToInt32(th) && Convert.ToInt32(strResult4[1]) < Convert.ToInt32(tmax))
            {
                label3.ForeColor = Color.Orange;
                this.temp.SelectAll();
                temp.SelectionColor = Color.Orange;
                ExecuteSQL("insert into Warninfo (报警时间,货物ID,传感器类型,数据序号,报警数据,报警级别) values('" + DateTime.Now.ToString() + "','" + id + "','温度','" + i + "','" + strResult4[1] + "','偏高')");


            }
            //偏低
            else if (Convert.ToInt32(strResult4[1]) > Convert.ToInt32(tmin) && Convert.ToInt32(strResult4[1]) <= Convert.ToInt32(tl))
            {
                label3.ForeColor = Color.Olive;
                this.temp.SelectAll();
                temp.SelectionColor = Color.Olive;
                ExecuteSQL("insert into Warninfo (报警时间,货物ID,传感器类型,数据序号,报警数据,报警级别) values('" + DateTime.Now.ToString() + "','" + id + "','温度','" + i + "','" + strResult4[1] + "','偏低')");
                string[] strR = sensor.getOrSetStepMotor(strIP, strPort, "104", "", "", "", "3", "2", 0);
              
            }

                //危险
            else if (Convert.ToInt32(strResult4[1]) >= Convert.ToInt32(tmax))
            {
                label3.ForeColor = Color.Red;
                this.temp.SelectAll();
                temp.SelectionColor = Color.Red;
                ExecuteSQL("insert into Warninfo (报警时间,货物ID,传感器类型,数据序号,报警数据,报警级别) values('" + DateTime.Now.ToString() + "','" + id + "','温度','" + i + "','" + strResult4[1] + "','危险')");
                string[] strR = sensor.getOrSetStepMotor(strIP, strPort, "104", "", "", "", "3", "1", 0);
               
            }
            //紧急
            else if (Convert.ToInt32(strResult4[1]) <= Convert.ToInt32(tmax))
            {
                label3.ForeColor = Color.Blue;
                this.temp.SelectAll();
                temp.SelectionColor = Color.Blue;
                ExecuteSQL("insert into Warninfo (报警时间,货物ID,传感器类型,数据序号,报警数据,报警级别) values('" + DateTime.Now.ToString() + "','" + id + "','温度','" + i + "','" + strResult4[1] + "','紧急')");

                string[] strR = sensor.getOrSetStepMotor(strIP, strPort, "104", "", "", "", "3", "2", 0);
                
            }



            string address = textBox1.Text.Trim();
            //rfid
            string strSensorID6 = "201";
            string[] strResult6 = sensor.getOrSetRFID(strIP, strPort, strSensorID6, "", "", "");
            ExecuteSQL("insert into 即时信(cid,type,data,time) values('" + id + "','到达地点','" + address + "','" + DateTime.Now.ToString() + "')");
            
            if (strResult6[1] != "00000000")
            {

                if (strResult6[1] == "89C77CB5")
                {
                    this.textBox1.Text = "北京";
                    
                }
                if (strResult6[1] == "27228DB5") {
                    this.textBox1.Text = "济南";
                }
                if (strResult6[1] == "9EA67CB5")
                {
                    this.textBox1.Text = "上海";
                }

            }


            this.richTextBox1.AppendText("rfid状态：" + strResult6[0] + "信息：" + strResult6[1]);

            textBox5.Text = DateTime.Now.ToString();
           

          
            i++;
        }
        /// <summary>
        // / 打开数据库
        /// </summary>
        public void open()
        {
            try
            {

                string connstring = "server=.\\SQLEXPRESS;uid=;pwd=;database=VDRMS;Integrated Security = SSPI ";
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
            int count = -1;
            try
            {
                open();
                SqlCommand cmd = new SqlCommand(sqlstring, sqlconnection);
                count = cmd.ExecuteNonQuery();

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

        private void comboBox2_SelectedIndexChanged(object sender, EventArgs e)
        {

        }

        private void button2_Click(object sender, EventArgs e)
        {
            timerClock.Close();
        }

        private void textBox2_TextChanged(object sender, EventArgs e)
        {

        }

        private void humi_TextChanged(object sender, EventArgs e)
        {

        }

        private void axWindowsMediaPlayer1_Enter(object sender, EventArgs e)
        {

        }

        private void axWindowsMediaPlayer2_Enter(object sender, EventArgs e)
        {

        }

        private void axWindowsMediaPlayer3_Enter(object sender, EventArgs e)
        {

        }

        private void axWindowsMediaPlayer4_Enter(object sender, EventArgs e)
        {

        }

        private void axWindowsMediaPlayer5_Enter(object sender, EventArgs e)
        {

        }

        private void fire_TextChanged(object sender, EventArgs e)
        {

        }

    }
}
