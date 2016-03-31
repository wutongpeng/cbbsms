namespace 道路运输货物信息状态检测系统
{
    partial class 主界面
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.menuStrip1 = new System.Windows.Forms.MenuStrip();
            this.即时信息ToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.历史信息ToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.报警信息ToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.货物管理ToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.网关设置ToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.网关设置ToolStripMenuItem1 = new System.Windows.Forms.ToolStripMenuItem();
            this.退出系统ToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.menuStrip1.SuspendLayout();
            this.SuspendLayout();
            // 
            // menuStrip1
            // 
            this.menuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.即时信息ToolStripMenuItem,
            this.历史信息ToolStripMenuItem,
            this.报警信息ToolStripMenuItem,
            this.货物管理ToolStripMenuItem,
            this.网关设置ToolStripMenuItem,
            this.网关设置ToolStripMenuItem1,
            this.退出系统ToolStripMenuItem});
            this.menuStrip1.Location = new System.Drawing.Point(0, 0);
            this.menuStrip1.Name = "menuStrip1";
            this.menuStrip1.Size = new System.Drawing.Size(782, 25);
            this.menuStrip1.TabIndex = 0;
            this.menuStrip1.Text = "menuStrip1";
            this.menuStrip1.ItemClicked += new System.Windows.Forms.ToolStripItemClickedEventHandler(this.menuStrip1_ItemClicked);
            // 
            // 即时信息ToolStripMenuItem
            // 
            this.即时信息ToolStripMenuItem.Name = "即时信息ToolStripMenuItem";
            this.即时信息ToolStripMenuItem.Size = new System.Drawing.Size(68, 21);
            this.即时信息ToolStripMenuItem.Text = "即时信息";
            this.即时信息ToolStripMenuItem.Click += new System.EventHandler(this.即时信息ToolStripMenuItem_Click);
            // 
            // 历史信息ToolStripMenuItem
            // 
            this.历史信息ToolStripMenuItem.Name = "历史信息ToolStripMenuItem";
            this.历史信息ToolStripMenuItem.Size = new System.Drawing.Size(68, 21);
            this.历史信息ToolStripMenuItem.Text = "历史信息";
            this.历史信息ToolStripMenuItem.Click += new System.EventHandler(this.历史信息ToolStripMenuItem_Click);
            // 
            // 报警信息ToolStripMenuItem
            // 
            this.报警信息ToolStripMenuItem.Name = "报警信息ToolStripMenuItem";
            this.报警信息ToolStripMenuItem.Size = new System.Drawing.Size(68, 21);
            this.报警信息ToolStripMenuItem.Text = "报警信息";
            this.报警信息ToolStripMenuItem.Click += new System.EventHandler(this.报警信息ToolStripMenuItem_Click);
            // 
            // 货物管理ToolStripMenuItem
            // 
            this.货物管理ToolStripMenuItem.Name = "货物管理ToolStripMenuItem";
            this.货物管理ToolStripMenuItem.Size = new System.Drawing.Size(68, 21);
            this.货物管理ToolStripMenuItem.Text = "货物管理";
            this.货物管理ToolStripMenuItem.Click += new System.EventHandler(this.货物管理ToolStripMenuItem_Click);
            // 
            // 网关设置ToolStripMenuItem
            // 
            this.网关设置ToolStripMenuItem.Name = "网关设置ToolStripMenuItem";
            this.网关设置ToolStripMenuItem.Size = new System.Drawing.Size(68, 21);
            this.网关设置ToolStripMenuItem.Text = "阈值设置";
            this.网关设置ToolStripMenuItem.Click += new System.EventHandler(this.网关设置ToolStripMenuItem_Click);
            // 
            // 网关设置ToolStripMenuItem1
            // 
            this.网关设置ToolStripMenuItem1.Name = "网关设置ToolStripMenuItem1";
            this.网关设置ToolStripMenuItem1.Size = new System.Drawing.Size(68, 21);
            this.网关设置ToolStripMenuItem1.Text = "网关设置";
            this.网关设置ToolStripMenuItem1.Click += new System.EventHandler(this.网关设置ToolStripMenuItem1_Click);
            // 
            // 退出系统ToolStripMenuItem
            // 
            this.退出系统ToolStripMenuItem.Name = "退出系统ToolStripMenuItem";
            this.退出系统ToolStripMenuItem.Size = new System.Drawing.Size(68, 21);
            this.退出系统ToolStripMenuItem.Text = "退出系统";
            this.退出系统ToolStripMenuItem.Click += new System.EventHandler(this.退出系统ToolStripMenuItem_Click);
            // 
            // 主界面
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.SystemColors.GradientActiveCaption;
            this.ClientSize = new System.Drawing.Size(782, 408);
            this.Controls.Add(this.menuStrip1);
            this.ForeColor = System.Drawing.SystemColors.Desktop;
            this.MainMenuStrip = this.menuStrip1;
            this.Name = "主界面";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "主界面";
            this.Load += new System.EventHandler(this.主界面_Load);
            this.menuStrip1.ResumeLayout(false);
            this.menuStrip1.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.MenuStrip menuStrip1;
        private System.Windows.Forms.ToolStripMenuItem 即时信息ToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem 历史信息ToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem 报警信息ToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem 货物管理ToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem 网关设置ToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem 网关设置ToolStripMenuItem1;
        private System.Windows.Forms.ToolStripMenuItem 退出系统ToolStripMenuItem;

    }
}