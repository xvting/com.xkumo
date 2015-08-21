using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace XStockDataCollectorTest
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            try
            {
                ServiceReference1.StockServiceDelegateClient client = new ServiceReference1.StockServiceDelegateClient();

                XStockDataCollectorTest.ServiceReference1.sinaDailyStockData data = client.getData("399001", "sz");

                MessageBox.Show("OK");
            }
            catch (Exception ex)
            {

                MessageBox.Show(ex.Message);
            }
            
        }
    }
}
