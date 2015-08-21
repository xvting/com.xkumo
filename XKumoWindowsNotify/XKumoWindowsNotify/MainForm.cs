using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading;
using System.Windows.Forms;

namespace XKumoWindowsNotify
{
    public partial class MainForm : Form
    {
        public MainForm()
        {
            InitializeComponent();
        }

        private void Form1_SizeChanged(object sender, EventArgs e)
        {
            if (this.WindowState == FormWindowState.Minimized)
            {
                this.Hide();
                this.notifyIconXkumo.Visible = true;
            }
        }

        private void notifyIconXkumo_MouseDoubleClick(object sender, MouseEventArgs e)
        {
            this.Visible = true;

            this.WindowState = FormWindowState.Normal;

            this.notifyIconXkumo.Visible = true; 

        }

        private void Form1_Load(object sender, EventArgs e)
        {
 
        }

        private void toolStripMenuItemExit_Click(object sender, EventArgs e)
        {
            falseClose = false;
            Application.Exit();
        }

        bool falseClose = true;

        private void Form1_FormClosing(object sender, FormClosingEventArgs e)
        {
            if (falseClose)
            {
                this.Hide();
                this.notifyIconXkumo.Visible = true;

                e.Cancel = true;

                falseClose = false;
            }
            else
            {

            }
        }

        private void toolStripMenuItemrunReport_Click(object sender, EventArgs e)
        {
            System.Diagnostics.Process pExecuteEXE = new System.Diagnostics.Process();
            pExecuteEXE.StartInfo.FileName = @"E:\Delphi.exe";
            pExecuteEXE.StartInfo.Arguments = "'paramstr1 paramstr2,paramstr3'";
            pExecuteEXE.Start();
            pExecuteEXE.WaitForExit();
        }

        private void btnStart_Click(object sender, EventArgs e)
        {
            backgroundWorker1.RunWorkerAsync(1000); 
        }

        private void backgroundWorker1_DoWork(object sender, DoWorkEventArgs e)
        {
            for (int i = 1; i < 11; i++)
            {

                Thread.Sleep(2000);

                backgroundWorker1.ReportProgress(i * 10);

            } 


        }

        private void backgroundWorker1_ProgressChanged(object sender, ProgressChangedEventArgs e)
        {
            progressBar1.Value = e.ProgressPercentage; 
        }

        private void contextMenuFile_Opening(object sender, CancelEventArgs e)
        {

        }

        private void testToolStripMenuItem_Click(object sender, EventArgs e)
        {
              FormTest testForm = new FormTest();

              testForm.ShowDialog();
        }
    }
}
