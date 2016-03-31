namespace 道路运输货物信息状态检测系统
{
    partial class 货物添加
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
            this.components = new System.ComponentModel.Container();
            this.label2 = new System.Windows.Forms.Label();
            this.textBox1 = new System.Windows.Forms.TextBox();
            this.dataGridView1 = new System.Windows.Forms.DataGridView();
            this.button1 = new System.Windows.Forms.Button();
            this.button2 = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.vDRMSDataSet = new 道路运输货物信息状态检测系统.VDRMSDataSet();
            this.货物BindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.货物TableAdapter = new 道路运输货物信息状态检测系统.VDRMSDataSetTableAdapters.货物TableAdapter();
            this.货物IDDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.货物名DataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.添加时间DataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.vDRMSDataSet)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.货物BindingSource)).BeginInit();
            this.SuspendLayout();
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("宋体", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.label2.Location = new System.Drawing.Point(47, 34);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(136, 16);
            this.label2.TabIndex = 1;
            this.label2.Text = "请输入货物名称：";
            this.label2.Click += new System.EventHandler(this.label2_Click);
            // 
            // textBox1
            // 
            this.textBox1.Location = new System.Drawing.Point(189, 32);
            this.textBox1.Name = "textBox1";
            this.textBox1.Size = new System.Drawing.Size(137, 21);
            this.textBox1.TabIndex = 3;
            this.textBox1.TextChanged += new System.EventHandler(this.textBox1_TextChanged);
            // 
            // dataGridView1
            // 
            this.dataGridView1.AllowUserToOrderColumns = true;
            this.dataGridView1.AutoGenerateColumns = false;
            this.dataGridView1.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridView1.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.货物IDDataGridViewTextBoxColumn,
            this.货物名DataGridViewTextBoxColumn,
            this.添加时间DataGridViewTextBoxColumn});
            this.dataGridView1.DataSource = this.货物BindingSource;
            this.dataGridView1.Location = new System.Drawing.Point(12, 122);
            this.dataGridView1.Name = "dataGridView1";
            this.dataGridView1.RowTemplate.Height = 23;
            this.dataGridView1.Size = new System.Drawing.Size(344, 146);
            this.dataGridView1.TabIndex = 4;
            this.dataGridView1.CellContentClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.dataGridView1_CellContentClick);
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(354, 32);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(55, 24);
            this.button1.TabIndex = 5;
            this.button1.Text = "添加";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // button2
            // 
            this.button2.Location = new System.Drawing.Point(437, 32);
            this.button2.Name = "button2";
            this.button2.Size = new System.Drawing.Size(61, 24);
            this.button2.TabIndex = 6;
            this.button2.Text = "查询";
            this.button2.UseVisualStyleBackColor = true;
            this.button2.Click += new System.EventHandler(this.button2_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("宋体", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.label1.Location = new System.Drawing.Point(12, 103);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(88, 16);
            this.label1.TabIndex = 7;
            this.label1.Text = "查询结果：";
            // 
            // vDRMSDataSet
            // 
            this.vDRMSDataSet.DataSetName = "VDRMSDataSet";
            this.vDRMSDataSet.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema;
            // 
            // 货物BindingSource
            // 
            this.货物BindingSource.DataMember = "货物";
            this.货物BindingSource.DataSource = this.vDRMSDataSet;
            // 
            // 货物TableAdapter
            // 
            this.货物TableAdapter.ClearBeforeFill = true;
            // 
            // 货物IDDataGridViewTextBoxColumn
            // 
            this.货物IDDataGridViewTextBoxColumn.DataPropertyName = "货物ID";
            this.货物IDDataGridViewTextBoxColumn.HeaderText = "货物ID";
            this.货物IDDataGridViewTextBoxColumn.Name = "货物IDDataGridViewTextBoxColumn";
            this.货物IDDataGridViewTextBoxColumn.ReadOnly = true;
            // 
            // 货物名DataGridViewTextBoxColumn
            // 
            this.货物名DataGridViewTextBoxColumn.DataPropertyName = "货物名";
            this.货物名DataGridViewTextBoxColumn.HeaderText = "货物名";
            this.货物名DataGridViewTextBoxColumn.Name = "货物名DataGridViewTextBoxColumn";
            // 
            // 添加时间DataGridViewTextBoxColumn
            // 
            this.添加时间DataGridViewTextBoxColumn.DataPropertyName = "添加时间";
            this.添加时间DataGridViewTextBoxColumn.HeaderText = "添加时间";
            this.添加时间DataGridViewTextBoxColumn.Name = "添加时间DataGridViewTextBoxColumn";
            // 
            // 货物添加
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(575, 366);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.button2);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.dataGridView1);
            this.Controls.Add(this.textBox1);
            this.Controls.Add(this.label2);
            this.Name = "货物添加";
            this.Text = "货物添加";
            this.Load += new System.EventHandler(this.货物添加_Load);
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.vDRMSDataSet)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.货物BindingSource)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox textBox1;
        private System.Windows.Forms.DataGridView dataGridView1;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.Button button2;
        private System.Windows.Forms.Label label1;
        private VDRMSDataSet vDRMSDataSet;
        private System.Windows.Forms.BindingSource 货物BindingSource;
        private VDRMSDataSetTableAdapters.货物TableAdapter 货物TableAdapter;
        private System.Windows.Forms.DataGridViewTextBoxColumn 货物IDDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn 货物名DataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn 添加时间DataGridViewTextBoxColumn;
    }
}