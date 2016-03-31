using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace 道路运输货物信息状态检测系统
{
    public partial class 主界面 : Form
    {
        public 主界面()
        {
            InitializeComponent();
        }

        private void toolStripButton1_Click(object sender, EventArgs e)
        {

        }

        private void 即时信息ToolStripMenuItem_Click(object sender, EventArgs e)
        {
            即时信息 即时 = new 即时信息();
            即时.Show();
        }

        private void 历史信息ToolStripMenuItem_Click(object sender, EventArgs e)
        {
            历史信息 lishi = new 历史信息();
            lishi.Show();
        }

        private void 报警信息ToolStripMenuItem_Click(object sender, EventArgs e)
        {
            报警信息 报警 = new 报警信息();
            报警.Show();
        }

        private void 货物管理ToolStripMenuItem_Click(object sender, EventArgs e)
        {
            货物管理 货物 = new 货物管理();
            货物.Show();
        }

        private void 网关设置ToolStripMenuItem_Click(object sender, EventArgs e)
        {
            阈值设置 阈值 = new 阈值设置();
            阈值.Show();
        }

        private void 网关设置ToolStripMenuItem1_Click(object sender, EventArgs e)
        {
           网关设置 网关 = new 网关设置();
           网关.Show();
        }

       
        private void 退出系统ToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (MessageBox.Show("是否要退出本系统？", "提示", MessageBoxButtons.YesNo, MessageBoxIcon.Information) == DialogResult.Yes)
            {
                Application.Exit();

            } 
        }

        private void menuStrip1_ItemClicked(object sender, ToolStripItemClickedEventArgs e)
        {

        }

        private void 主界面_Load(object sender, EventArgs e)
        {

        }
    }
}
