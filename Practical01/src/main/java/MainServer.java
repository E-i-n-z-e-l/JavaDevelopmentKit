public class MainServer {
    public static void main(String[] args) {
        ServerWindow serverwindow = new ServerWindow();
        new ClientGUI(serverwindow);
        new ClientGUI(serverwindow);
    }
}
