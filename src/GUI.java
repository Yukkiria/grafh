
import java.awt.*;
import java.awt.event.*;
import java.awt.Menu;
//создаем граф
class DiagGraph extends Dialog {//создает диалоговое окно с созданием графа, выбираешь количество вершин
    TextField tfX;    Graph graph;
    public DiagGraph(Graph gr, Frame parent, String title) {
        super(parent, title, true);
        setLayout(new FlowLayout()); //менеджер компоновки, потоковое расположение
        setSize(180,150);        graph = gr;        Label lbX;        Button btOk, btCancel;
        lbX = new Label("N");        tfX = new TextField(10);        btOk = new Button("Ввод");
        btCancel = new Button("Отмена");

        add(lbX);        add(tfX);        add(btOk);        add(btCancel);
        btOk.addActionListener(new OkListener());
        btCancel.addActionListener(new CancelListener());    }

    class OkListener implements ActionListener {
        public void actionPerformed(ActionEvent ac) throws NumberFormatException {
            int n = Integer.parseInt(tfX.getText());            graph.SetGraph(n); //Задает n вершин
            dispose();            setVisible(false);        }    }

    class CancelListener implements ActionListener {
        public void actionPerformed(ActionEvent ac) {
            dispose();  //освобождение ресурсов окна
            setVisible(false);        }    }  }

class JoinGraph extends Dialog {  //соединяем вершины графа
    TextField tfX,tfY;    Graph graph;
    public JoinGraph(Graph gr, Frame parent, String title) {
        super(parent,title,true);        setLayout(new FlowLayout());        setSize(200,150);
        graph = gr;        Label lbX,lbY;        Button btOk, btCancel;        lbX = new Label("От");
        lbY = new Label("К");        tfX = new TextField(10);        tfY = new TextField(10);
        btOk = new Button("Ввод");        btCancel = new Button("Отмена");

        add(lbX);        add(lbY);        add(tfX);        add(tfY);        add(btOk);        add(btCancel);

        btOk.addActionListener(new OkListener());
        btCancel.addActionListener(new CancelListener());    }

    class OkListener implements ActionListener{
        public void actionPerformed(ActionEvent ac) throws NumberFormatException {
            int x1 = Integer.parseInt(tfX.getText());
            int x2 = Integer.parseInt(tfY.getText());
            graph.joinElem(x1,x2);            repaint();            setVisible(false);        }    }

    class CancelListener implements ActionListener {
        public void actionPerformed(ActionEvent ac) {
            dispose();            setVisible(false);        }    }  }

class setParam extends Dialog { //задаем параметры пути

    TextField tfV1,tfV2, tfVN, tfVK;    TextField rebstart;    TextField rebfinal;
    Graph graph;
    public setParam(Graph gr, Frame parent, String title) {
        super(parent,title,true);        setLayout(new FlowLayout());        graph = gr; setSize(80,370);
        Label lbv1;        Label name1;        Label lbl2;        Label lbl3;
        Button btOk, btCancel;        lbv1 = new Label("Вершина");        tfV1 = new TextField(10);
        lbl2 = new Label("Начало пути");        tfVN = new TextField(10);
        lbl3 = new Label("Конец пути");        tfVK = new TextField(10);
        name1 = new Label("      Ребро       ");        rebstart = new TextField(5);
        rebfinal = new TextField(5);        btOk = new Button("Ввод");
        btCancel = new Button("Отмена");
        add(lbl2);        add(tfVN);        add(lbl3);        add(tfVK);        add(lbv1);        add(tfV1);
        add(name1);        add(rebstart);        add(rebfinal);        add(btOk);        add(btCancel);
        btOk.addActionListener(new OkListener());
        btCancel.addActionListener(new CancelListener());    }

    class OkListener implements ActionListener {
        public void actionPerformed(ActionEvent ac) throws NumberFormatException {
            int vn = Integer.parseInt(tfVN.getText());
            int vk = Integer.parseInt(tfVK.getText());
            int x1 = Integer.parseInt(tfV1.getText());
            int x2 = Integer.parseInt(rebstart.getText());
            int x3 = Integer.parseInt(rebfinal.getText());
            graph.AllPathes(vn, vk, x1, x2, x3);    repaint();     dispose();  setVisible(false);   }    }

    class CancelListener implements ActionListener{
        public void actionPerformed(ActionEvent ac){    dispose();     setVisible(false);   }   }   }

class MenuFrame extends Frame { //Menuframe расширение класса Frame
    Graph gr;  boolean flag = false; //граф из примера
    public MenuFrame() {        gr = new Graph();        MenuBar mb = new MenuBar();
        Menu file = new Menu("Файл");        Menu func = new Menu("Граф");
        mb.add(file);        mb.add(func);
        MenuItem quit = new MenuItem("Выход");        file.add(quit);
        MenuItem newgraph = new MenuItem("Создать граф");
        MenuItem joinline = new MenuItem("Добавить ребро");
        MenuItem fullgraph = new MenuItem("Полный граф");
        MenuItem find  = new MenuItem("Найти путь");
        MenuItem example = new MenuItem("Пример");
        func.add(newgraph);        func.add(joinline);        func.add(fullgraph);
        func.add(find);        func.add(example);        this.setMenuBar(mb);

        //добавляем блок прослушки
        quit.addActionListener(new HandQuit());
        newgraph.addActionListener(new MakeGraph(this));
        joinline.addActionListener(new JoinElem(this));
        find.addActionListener(new setForPath(this));
        fullgraph.addActionListener(new Full());
        example.addActionListener(new Example());

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent wc) {    System.exit(0);      }      });
        this.setVisible(true);    }

    class HandQuit implements ActionListener { //реализует интерфейс, слушатель событий
        public void actionPerformed(ActionEvent ae) { //метод, обрабатывающий событие
            System.exit(0);        }    }

    class Example implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            flag = true;      int n = 9;          gr.SetGraph(n);
            gr.joinElem(1, 2);  gr.joinElem(1, 3);  gr.joinElem(1, 4);  gr.joinElem(2, 3);
            gr.joinElem(2, 5);  gr.joinElem(3, 6);  gr.joinElem(3, 7);  gr.joinElem(4, 6);         gr.joinElem(5, 7);  gr.joinElem(5, 8);  gr.joinElem(6, 7);  gr.joinElem(6, 9);
            gr.joinElem(7, 8);  repaint();       }    }

    class MakeGraph implements ActionListener {
        MenuFrame menufr;
        MakeGraph(MenuFrame mf) {            menufr = mf;        }
        public void actionPerformed(ActionEvent ae){          gr.min=0;
            DiagGraph d = new DiagGraph(gr,menufr,"Создание Графа");
            d.setVisible(true);            repaint();        }    }

    class JoinElem implements ActionListener{        MenuFrame menufr;
        JoinElem(MenuFrame mf){            menufr = mf;        }
        public void actionPerformed(ActionEvent ae){
            JoinGraph d = new JoinGraph(gr,menufr,"Добавить ребро");
            d.setVisible(true);            repaint();        }    }

    class setForPath implements ActionListener{
        MenuFrame menufr;        setForPath(MenuFrame mf){            menufr = mf;        }

        public void actionPerformed(ActionEvent ae){
            if (flag == true) {
                demoGraph dd = new demoGraph(gr, menufr, "Найти путь");
                dd.setVisible(true);            repaint();                      }
            else {            setParam d = new setParam(gr,menufr,"Найти путь");
                d.setVisible(true);            repaint();          }        }    }

    class Full implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            for(int i =1; i <= gr.getSize(); i++)
                for( int j=1; j <= gr.getSize(); j++)
                    if(i != j)                        gr.joinElem(i,j);            repaint();        }    }

    public void paint(Graphics g) {
        Graphics2D gg = (Graphics2D) g;
        if (gr.getSize() > 0) {
            for(int i = 1; i <=gr.getSize() ;i++) {
                double degrees = (i * 360)/gr.getSize();double radian = Math.toRadians(degrees);
                double x = 200 * Math.sin(radian);    double y = 200 * Math.cos(radian);
                if((i) == gr.t1) {
                    gg.setColor(Color.BLUE);
                    gg.fillOval(((int)x-10) +400 ,((int)y-10) + 300,20,20);
                    gg.drawString(Integer.toString(i),(int)x + 420,(int)y +300);                }
                else {                    gg.setColor(Color.BLACK);
                    gg.drawOval(((int)x-10) +400 ,((int)y-10) + 300,20,20);
                    gg.drawString(Integer.toString(i),(int)x + 420,(int)y +300);                }            }

            for (int i = 1; i <= gr.getSize(); i++) {
                for (int j = 1; j <= gr.getSize(); j++) {
                    if (gr.isJoin(i, j) == 1) {
                        double degrees1 = (i * 360) / gr.getSize(); double radian1 = Math.toRadians(degrees1);
                        double x1 = 200 * Math.sin(radian1);     double y1 = 200 * Math.cos(radian1);
                        double degrees2 = (j * 360) / gr.getSize();
                        double radian2 = Math.toRadians(degrees2);
                        double x2 = 200 * Math.sin(radian2);       double y2 = 200 * Math.cos(radian2);
                        if (i == gr.start1 && j == gr.final1) {
                            gg.setColor(Color.orange);
                            gg.setStroke(new BasicStroke(10.0f));
                            gg.drawLine(((int) x1) +400, ((int) y1)+300, ((int) x2)+400, ((int) y2 + 300));
                        }
                        else {
                            gg.setColor(Color.BLACK);
                            gg.setStroke(new BasicStroke(1.0f));
                            gg.drawLine(((int) x1) + 400, ((int) y1) + 300, ((int) x2) + 400, ((int) y2 + 300));  }  }  }  }

            if(gr.min != 0 )
                for(int i =1; i < gr.min; i++) {
                    if (gr.path[i]!=gr.start1 && gr.path[i+1]!=gr.final1) {
                        gg.setColor(Color.RED);
                        gg.setStroke(new BasicStroke(3.0f));
                        double degrees1 = (gr.path[i] * 360) / gr.getSize();
                        double radian1 = Math.toRadians(degrees1);
                        double x1 = 200 * Math.sin(radian1);   double y1 = 200 * Math.cos(radian1);
                        double degrees2 = (gr.path[i + 1] * 360) / gr.getSize();
                        double radian2 = Math.toRadians(degrees2);
                        double x2 = 200 * Math.sin(radian2);      double y2 = 200 * Math.cos(radian2);
                        gg.drawLine(((int) x1) + 400, ((int) y1) + 300, ((int) x2) + 400, ((int) y2 + 300));
                    }                }        }    }

    class demoGraph extends Dialog {
        TextField tfV1,tfV2, tfVN, tfVK;   TextField reb1b;  TextField reb1e;  Graph graph;
        public demoGraph(Graph gr, Frame parent, String title) {
            super(parent,title,true);            setLayout(new FlowLayout());
            graph = gr;  setSize(80,370);  Label lbv1;    Label name1;     Label lbl2;     Label lbl3;
            Button btOk, btCancel;
            lbv1 = new Label("Вершина");   tfV1 = new TextField(10);    tfV1.setText("3");
            lbl2 = new Label("Начало пути");    tfVN = new TextField(10);     tfVN.setText("2");
            lbl3 = new Label("Конец пути");     tfVK = new TextField(10);      tfVK.setText("7");
            name1 = new Label("Ребро");    reb1b = new TextField(5);        reb1b.setText("2");
            reb1e = new TextField(5);    reb1e.setText("3");
            btOk = new Button("Ввод");      btCancel = new Button("Отмена");
            add(lbl2);          add(tfVN);        add(lbl3);       add(tfVK);       add(lbv1);           add(tfV1);
            add(name1);    add(reb1b);       add(reb1e);    add(btOk);        add(btCancel);
            btOk.addActionListener(new OkListener());
            btCancel.addActionListener(new CancelListener());        }

        class OkListener implements ActionListener{
            public void actionPerformed(ActionEvent ac) throws NumberFormatException{
                int vn = Integer.parseInt(tfVN.getText());
                int vk = Integer.parseInt(tfVK.getText());
                int x1 = Integer.parseInt(tfV1.getText());
                int x2 = Integer.parseInt(reb1b.getText());
                int x3 = Integer.parseInt(reb1e.getText());
                graph.AllPathes(vn, vk, x1, x2, x3);   repaint();     dispose();   setVisible(false);  }   }

        class CancelListener implements ActionListener{
            public void actionPerformed(ActionEvent ac){   dispose();  setVisible(false);  }
        }
    }
}