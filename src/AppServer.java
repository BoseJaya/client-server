import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
public class AppServer extends Frame implements ActionListener,Runnable{
    Button b1;
    TextField tf;
    TextArea ta;

    ServerSocket ss;
    Socket s;

    PrintWriter pw;
    BufferedReader br;

    Thread th;
    public AppServer()
    {
        Frame f=new Frame("Server side Chatting");
        f.setLayout(new FlowLayout());
        f.setBackground(Color.orange);
        b1=new Button("send");
        b1.addActionListener(this);
        tf=new TextField(15);
        ta=new TextArea(12,20);
        f.addWindowListener(new w1());
        f.add(tf);
        f.add(b1)        ;
        f.add(ta);

        try
        {
            ss=new ServerSocket(12000);
            s=ss.accept();
            br=new BufferedReader(new InputStreamReader(s.getInputStream()));
            pw=new PrintWriter(s.getOutputStream(),true);
        }
        catch (Exception e)
        {
            e.getStackTrace();
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
        AppServer a=new AppServer();
    }
}
