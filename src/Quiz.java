import java.awt.event.*;
        import java.awt.*;
        import javax.swing.*;

public class Quiz implements ActionListener {

    String[] questions = {
            " Czy zajmowałeś się już hodowlą ptaszników?",
            " Czy preferujesz ptaszniki o spokojnym usposobieniu?",
            " Jak duży budżet zamierzasz przeznaczyć na zakup ptasznika?",
            "Jak dobrze znasz zasady dotyczące żywienia ptaszników?",
            " Czy jesteś w stanie zapewnić pająkowi odpowiednią temperaturę, wilgotność, oraz ogólne warunki życia?"
    };

    String[][] options = {
            {"Nie, nigdy", "Tak, mam jednego/dwa","Tak, mam 3-5", "Tak, mam więcej niż 5"},
            {"Zdecydowanie tak", "Raczej tak", "Niekoniecznie", "Nie"},
            {"Do 50 zł", "50-100 zł", "~150 zł", "200< zł"},
            {"Słabo", "Przeciętnie", "Dobrze", "Bardzo Dobrze"  },
            {"Nie wiem", "Postaram się", "Tak, potrafię określić odpowiednie warunki 'na oko'", "Tak, i posiadam ku temu odpowiednie przyrządy" }

    };
    char[] answers = {
            'A',
            'B',
            'C',
            'D',
            'D'
    };
    char guess;
    char answer;
    char index;
    int points_earned =0;
    int total_questions = questions.length;
    int result;
    int seconds = 60;

    JFrame frame = new JFrame();
    JTextField textfield = new JTextField();
    JTextArea textarea = new JTextArea();
    JButton buttonA = new JButton();
    JButton buttonB = new JButton();
    JButton buttonC = new JButton();
    JButton buttonD = new JButton();
    JLabel answer_labelA = new JLabel();
    JLabel answer_labelB = new JLabel();
    JLabel answer_labelC = new JLabel();
    JLabel answer_labelD = new JLabel();
    JLabel time_label = new JLabel();
    JLabel seconds_left = new JLabel();
    JTextArea punkty = new JTextArea();

    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            seconds--;
            seconds_left.setText(String.valueOf(seconds));
            if(seconds<=0) {
                displayAnswer();
            }

        }
    });


    public Quiz()  {
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(1200,650);
        frame.getContentPane().setBackground(new Color(50,50,55));
        frame.setLayout(null);
        frame.setResizable(false);

        textfield.setBounds(0,0,1200,50);
        textfield.setBackground(new Color(25,25,25));
        textfield.setForeground(new Color(255,255,255));
        textfield.setFont(new Font("Ink Free",Font.BOLD,30));
        textfield.setBorder(BorderFactory.createBevelBorder(1));
        textfield.setHorizontalAlignment(JTextField.CENTER);
        textfield.setEditable(false);


        textarea.setBounds(0,50,1200,50);
        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);
        textarea.setBackground(new Color(25,25,25));
        textarea.setForeground(new Color(255,255,255));
        textarea.setFont(new Font("Ink Free",Font.BOLD,22));
        textarea.setBorder(BorderFactory.createBevelBorder(1));
        textarea.setEditable(false);


        buttonA.setBounds(20,110,100,100);
        buttonA.setFont(new Font ("Ink Free",Font.BOLD,35));
        buttonA.setFocusable(false);
        buttonA.addActionListener(this);
        buttonA.setText("A");

        buttonB.setBounds(20,220,100,100);
        buttonB.setFont(new Font ("Ink Free",Font.BOLD,35));
        buttonB.setFocusable(false);
        buttonB.addActionListener(this);
        buttonB.setText("B");

        buttonC.setBounds(20,330,100,100);
        buttonC.setFont(new Font ("Ink Free",Font.BOLD,35));
        buttonC.setFocusable(false);
        buttonC.addActionListener(this);
        buttonC.setText("C");

        buttonD.setBounds(20,440,100,100);
        buttonD.setFont(new Font ("Ink Free",Font.BOLD,35));
        buttonD.setFocusable(false);
        buttonD.addActionListener(this);
        buttonD.setText("D");

        answer_labelA.setBounds(150,110,1100,100);
        answer_labelA.setBackground(new Color(50,50,50));
        answer_labelA.setForeground(new Color(255,255,255));
        answer_labelA.setFont(new Font("Ink Free",Font.PLAIN, 30));


        answer_labelB.setBounds(150,220,1100,100);
        answer_labelB.setBackground(new Color(50,50,50));
        answer_labelB.setForeground(new Color(255,255,255));
        answer_labelB.setFont(new Font("Ink Free",Font.PLAIN, 30));


        answer_labelC.setBounds(150,330,1100,100);
        answer_labelC.setBackground(new Color(50,50,50));
        answer_labelC.setForeground(new Color(255,255,255));
        answer_labelC.setFont(new Font("Ink Free",Font.PLAIN, 30));


        answer_labelD.setBounds(150,440,1100,100);
        answer_labelD.setBackground(new Color(50,50,50));
        answer_labelD.setForeground(new Color(255,254,255));
        answer_labelD.setFont(new Font("Ink Free",Font.PLAIN, 30));


        seconds_left.setBounds(1100,510,100,100);
        seconds_left.setBackground(new Color(25,25,25));
        seconds_left.setForeground(new Color(200,0,0));
        seconds_left.setFont(new Font("Ink Free", Font.BOLD, 50));
        seconds_left.setBorder(BorderFactory.createBevelBorder(1));
        seconds_left.setOpaque(true);
        seconds_left.setHorizontalAlignment(JTextField.CENTER);
        seconds_left.setText(String.valueOf(seconds));

        time_label.setBounds(890,475,400,25);
        time_label.setBackground(new Color(50,50,50));
        time_label.setForeground(new Color(255,255,255));
        time_label.setFont(new Font ("Ink Free", Font.PLAIN, 20));
        time_label.setHorizontalAlignment(JTextField.CENTER);
        time_label.setText("Następne pytanie za:");



        punkty.setBounds(200,200,350,420);
        punkty.setBackground(new Color(25,25,25));
        punkty.setForeground(new Color(255,255,255));
        punkty.setFont(new Font ("Ink Free", Font.BOLD, 16));
        //punkty.setBorder(BorderFactory.createBevelBorder(2));
        punkty.setEditable(false);
        punkty.setLineWrap(true);
        punkty.setWrapStyleWord(true);
        punkty.setAlignmentY(JTextField.CENTER);



        frame.add(time_label);
        frame.add(seconds_left);
        frame.add(answer_labelA);
        frame.add(answer_labelB);
        frame.add(answer_labelC);
        frame.add(answer_labelD);
        frame.add(buttonA);
        frame.add(buttonB);
        frame.add(buttonC);
        frame.add(buttonD);

        frame.add(textarea);
        frame.add(textfield);
        frame.setVisible(true);

        nextQuestion();

    }
    public void nextQuestion() {

        if(index>=total_questions){
            results();
        }
        else {
            textfield.setText("Pytanie " +(index+1));
            textarea.setText(questions[index]);
            answer_labelA.setText(options[index][0]);
            answer_labelB.setText(options[index][1]);
            answer_labelC.setText(options[index][2]);
            answer_labelD.setText(options[index][3]);
            timer.start();

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        if(e.getSource()==buttonA) {
            answer = 'A';
            //if(answer == answers[index]) {
            points_earned=1;
            //}
        }
        if(e.getSource()==buttonB) {
            answer = 'B';
            //if(answer == answers[index]) {
            points_earned=points_earned+2;
            //}
        }
        if(e.getSource()==buttonC) {
            answer = 'C';
            //if(answer == answers[index]) {
            points_earned=points_earned+3;
            // }
        }
        if(e.getSource()==buttonD) {
            answer = 'D';
            // if(answer == answers[index]) {

            points_earned=points_earned+4;
            // }
        }
        displayAnswer();
    }
    public void displayAnswer() {

        timer.stop();

        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        /*if(answers[index] != 'A')
            answer_labelA.setForeground(new Color(255,0,0));
        if(answers[index] != 'B')
            answer_labelB.setForeground(new Color(255,0,0));
        if(answers[index] != 'C')
            answer_labelC.setForeground(new Color(255,0,0));
        if(answers[index] != 'D')
            answer_labelD.setForeground(new Color(255,0,0));*/

        Timer pause = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answer_labelA.setForeground(new Color(255,255,255));
                answer_labelB.setForeground(new Color(255,255,255));
                answer_labelC.setForeground(new Color(255,255,255));
                answer_labelD.setForeground(new Color(255,255,255));

                answer = ' ';
                seconds=60;
                seconds_left.setText(String.valueOf(seconds));
                buttonA.setEnabled(true);
                buttonB.setEnabled(true);
                buttonC.setEnabled(true);
                buttonD.setEnabled(true);
                index++;
                nextQuestion();


            }
        });
        pause.setRepeats(false);
        pause.start();
    }
    public void results() {

        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);


        textfield.setText("Rezultat:");
        textarea.setText("");
        answer_labelA.setText("");
        answer_labelB.setText("");
        answer_labelC.setText("");
        answer_labelD.setText("");
        String result;

        if (points_earned >= 18) {
            result = " Zaawansowany hodowca \n\n Polecany gatunek ptasznika: \n\n Poecilotheria Metallica \n\n  ! Posiada silny jad, który może zagrażać zdrowiu w tym życiu człowieka." +
                    "Gatunek uważany przez wielu za najpiękniejszy ze wszystkich ptaszników. Kolorem dominującym na " +
                    "całym ciele jest metaliczny, niebieski. Szybki i umiarkowanie agresywny pająk, który bardzo szybko" +
                    "porusza się i skacze.Ze względu na atrakcyjne ubarwienie osiąga bardzo wysokie ceny.(\n)";
        } else if (points_earned >= 10) {
            result = " Średnio zaawansowany \n\n Polecany gatunek ptasznika: \n\n  Psalmopoeus Irminia  \n\n  W naturze występuje w Wenezueli. " +
                    " Jest to gatunek nadrzewny, szybki i skoczny, może ukąsić, ale jego jad nie jest groźny." +
                    "Ma czarny odwłok z pomarańczowo-różowo-czarnym wzorem, odnóża czarne z różowo-pomarańczowymi" +
                    "plamami na ostatnich dwóch segmentach odnóży.(\n)";
        } else {
            result = " Początkujący pajęczarz \n\n Polecany gatunek ptasznika: \n\n  Acanthoscurria Geniculata (ptasznik białokolanowy) \n\n " +
                    " Idealny ptasznik dla początkujących – jest „puszysty”, ma ciekawe wybarwienie " +
                    "(białe paseczki na odnóżach), a dzięki swojej żarłoczności bardzo szybko rośnie. " +
                    "Dorosły osobnik ma naprawdę imponujące rozmiary – rozstaw nóg sięga nawet ponad 20 centymetrów.(\n)";
        }
        punkty.setText(points_earned + "/20 " + result);
        frame.add(punkty);


    }

    public void jButton1ActionPerformed(ActionEvent evt) {
    }
}

