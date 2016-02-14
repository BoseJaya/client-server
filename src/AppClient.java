import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class AppClient extends Frame implements ActionListener,Runnable{
    Button b1;
    TextField tf;
    TextArea ta;

    Socket s;
    PrintWriter pw;
    BufferedReader br;
    Thread th;
    public AppClient()
    {
        Frame f=new Frame("ClientSide Chatting");
        f.setLayout(new FlowLayout());
        f.setBackground(Color.green);
        b1=new Button("send");
        b1.addActionListener(this);
        tf=new TextField(15);
        ta=new TextArea(12,20);
        f.addWindowListener(new w1());
        f.add(tf);
        f.add(b1);
        f.add(ta);

        try
        {
          s=new Socket(InetAddress.getByName("192.168.0.7"),12000);
            /*if(s==null)
            {
                System.out.println("Not connected......");
                System.exit(0);

            }
            */
            br=new BufferedReader(new InputStreamReader(s.getInputStream()));
            pw=new PrintWriter(s.getOutputStream(),true);
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
            System.out.println("Connection has not YET established");
            System.exit(0);

        }
        th=new Thread(this);
        th.start();
        setFont(new Font("Arial",Font.BOLD,20));
        f.setSize(200,200);
        f.setLocation(300,300);
        f.setVisible(true);
        f.validate();
    }
    private class w1 extends WindowAdapter
    {
        public void windowClosing(WindowEvent we)
        {
            System.exit(0);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        pw.println(tf.getText());
        tf.setText("");

    }

    @Override
    public void run() {
        while(true)
        {
            try
            {
                ta.append(br.readLine()+"\n");
            }
            catch (Exception e)
            {

            }
        }
    }
    public static void main(String args[])
    {
        AppClient a=new AppClient();
    }

}
