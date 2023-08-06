using System;

namespace CS001_BasicCsharp
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Xin chao C#");
            string sInput = "";
            bool check = false;
            float a = 0;
            const double PI = 3.14;
            while (!check)
            {
                sInput = Console.ReadLine();
                try
                {
                    a = float.Parse(sInput);
                    check = true;
                    Console.WriteLine(a);
                }
                catch
                {

                    Console.WriteLine("Hay nhap dung gia tri so!");
                    check = false;
                }
            }
            double getPIData = PI * a;
            if (getPIData == 0)
            {
                Console.WriteLine("Sai data cua anh a roi");
            } else
            {
                Console.WriteLine("Gia tri du lieu la " + getPIData);
            }

            string[] strArr = new string[sInput.Length];
            int[] intArr = new int[sInput.Length];
            Console.WriteLine(strArr.Length);
            intArr.Max();
            Array.Sort(intArr);
            double[,] dbArray = new double[strArr.Length, 9];
            xinchao(ten: "Phu", ho: "Nguyen Thinh");
            xinchao(ho: "Nguyen Thinh", ten: "Phu");
            xinchao("Nguyen Van","Nam");
            xinchaoDefault("Nguyen Van");

        }


        static void xinchao(string ho, string ten)
        {
            string fullName;
            fullName = ho + " " + ten;
            Console.WriteLine($"Xin chao {fullName}");
        }

        static void xinchaoDefault(string ho, string ten = "Nguyen") {

            string fullName;
            fullName = ho + " " + ten;
            Console.WriteLine($"Xin chao {fullName}");

        }
    }
}