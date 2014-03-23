package bot;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

/**
 * Created by hp on 23.03.2014.
 */
public class Controller {
    private SerialPort serialPortOut;

    private Controller() {
        log = new LinkedList<String>();
    }

    private LinkedList<String> log;

    private void appendLog(String msg) {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.zzz");
        Date date = new Date();
        log.addFirst(String.format("%t;%s", dateFormat.format(date), msg));
    }

    static private Controller self;
    static public Controller getInstance() {
        if(self == null)
            self = new Controller();
        return self;
    }

    public void AddListener(IListener listener) throws SerialPortException {
        if(serialPortOut != null) {
            serialPortOut.addEventListener(new PortReader(serialPortOut, listener), SerialPort.MASK_RXCHAR);
        }
    }

    private void moveBot(String cmd) {
        if (serialPortOut != null)
            try {
                serialPortOut.writeString(cmd);
            } catch (SerialPortException e) {
                e.printStackTrace();
            }
    }

    private boolean connected;
    public void Connect(String port, IListener lstn) {
        serialPortOut = new SerialPort(port);
        try {
            serialPortOut.openPort();
            //Выставляем параметры
            serialPortOut.setParams(SerialPort.BAUDRATE_9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            serialPortOut.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN | SerialPort.FLOWCONTROL_RTSCTS_OUT);
            connected = true;
            if (lstn != null)
                AddListener(lstn);
        }
        catch (SerialPortException ex) {
            ex.printStackTrace();
        }
    }

    public void Disconnect() {
        if (serialPortOut != null)
            try {
                serialPortOut.closePort();
            } catch (SerialPortException e) {
                e.printStackTrace();
            }
        connected = false;
    }

    public void MoveForward() {
        moveBot("f>");
    }

    public void MoveLeft() {
        moveBot("l>");
    }

    public void MoveRight() {
        moveBot("r>");
    }

    public void MoveBack() {
        moveBot("b>");
    }

    public void Stop() {
        moveBot("s>");
    }

    public boolean isConnected() {
        return connected;
    }

    public LinkedList<String> getLog() {
        return log;
    }

    private static class PortReader implements SerialPortEventListener {

        private SerialPort port;
        private IListener lstn;
        public PortReader(SerialPort port, IListener listener) {
            this.port = port;
            lstn = listener;
        }

        public void serialEvent(SerialPortEvent event) {
            if(port != null && event.isRXCHAR() && event.getEventValue() > 0){
                try {
                    //Получаем ответ от устройства, обрабатываем данные и т.д.
                    String str = port.readString(event.getEventValue());
                    if (lstn != null)
                        lstn.Read(str);
                }
                catch (SerialPortException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }
}
