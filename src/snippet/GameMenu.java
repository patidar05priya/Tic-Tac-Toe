package snippet;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JOptionPane;


 
public class GameMenu extends javax.swing.JFrame {
    JButton button[];
    char sign[][];
    int rows=3;
    int columns=3;
    
    /** Creates new form GameMenu */
    public GameMenu() {
        initComponents();
        setLocationRelativeTo(null);
        button=new JButton[rows*columns];

        //set the Button to the Grid...
        for(int i=0;i<9;i++){
           button[i]=new JButton();
           button[i].setFocusPainted(false);
           button[i].setOpaque(false);
           button[i].setContentAreaFilled(false);
           button[i].setActionCommand( Integer.toString( i ) );
           button[i].setPreferredSize(new Dimension(60, 60));
           button[i].setFont(new Font("Tahoma", 1, 35));
           button[i].addActionListener(new actionListener());
           gridPanel.add(button[i]);

        }
        resetGame();
    }

	//Initialize Components...
    private void initComponents() {

        gridPanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblMessage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tic Tac Toe GAME - Developed By iXtremity...");
        setAlwaysOnTop(true);
        setResizable(false);

        gridPanel.setLayout(new java.awt.GridLayout(3, 3, 1, 1));

        jButton1.setText("Exit");
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Tic Tac Toe GAME ");

        lblMessage.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblMessage.setForeground(new java.awt.Color(204, 0, 0));
        lblMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(gridPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                .addGap(37, 37, 37))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gridPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(lblMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }


    //Reset the Game and Begin new Game...
    public void resetGame(){
        int respond=JOptionPane.showConfirmDialog(GameMenu.this, "Are You Want to Start New Game???", "New Game", JOptionPane.YES_NO_OPTION);
        if(respond==0){
            lblMessage.setText(null);
            sign=new char[rows][columns];
            for(int i=0;i<9;i++){
               button[i].setText(null);
            }

            Random randomNo=new Random();
            int indexNo=randomNo.nextInt(rows*columns);
            button[indexNo].setText("O");
            sign[indexNo/rows][indexNo%columns]='O';
        }else{
            System.exit(0);
        }
    }

    public class actionListener implements ActionListener {
        byte tmpRow,tmpColumn;

        public void actionPerformed(ActionEvent event) {
            JButton button=(JButton) event.getSource();
            int clickedRow=Integer.parseInt(button.getActionCommand())/rows;
            int clickedColumn=Integer.parseInt(button.getActionCommand())%columns;

            if(isAlreadyPlaced(clickedRow, clickedColumn)){
                lblMessage.setText("This Cell is Already Occupied.. Please Try Another..!!");
            }else{
                lblMessage.setText(null);
                button.setText("X");
                sign[clickedRow][clickedColumn]='X';

                if(isWin('X')){
                    lblMessage.setText("Game Over..");
                    JOptionPane.showMessageDialog(GameMenu.this, "You Have Won the Game!! :-)", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                    resetGame();
                    return;
                }else{

                    //User hasn't won.. So it's time for the Computer..
                    computerMove();

                    if(isWin('O')){
                        lblMessage.setText("Game Over..");
                        JOptionPane.showMessageDialog(GameMenu.this, "Computer Has Won the Game!! :-)", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                        resetGame();
                        return;
                    }
                }

            }

            if(isTie()){
                lblMessage.setText("Game Over..");
                JOptionPane.showMessageDialog(GameMenu.this, "It is a Tie..!! :-)", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                resetGame();
            }
        }

	//Check Whether this Cell Has Already Placed...
    public boolean isAlreadyPlaced(int clickedRow,int clickedColumn){
        boolean status;
        if(sign[clickedRow][clickedColumn]=='O' || sign[clickedRow][clickedColumn]=='X'){
            status=true;
        }else{
            status=false;
        }
        return status;
    }

	//Check Whether Game is Tie...
    public boolean isTie(){
        boolean tie=true;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if(sign[i][j]!='O' && sign[i][j]!='X'){
                tie=false;
                }
            }
        }
        return tie;
    }

	//Check Whether Game Win...
    public boolean isWin(char playerSign){
        boolean win=false;
        byte rowCount=0;
        byte columnCount=0;
        byte diagCount1=0;
        byte diagCount2=0;

        //Check Whether User or Computer Win the Game...
        for (int i = 0; i < rows; i++) {
            rowCount=0;
            columnCount=0;
            for (int j = 0; j < columns; j++) {

                //Check Vertically...
                if(sign[i][j]==playerSign){
                    rowCount++;
                }

                //Check Horizantally...
                if(sign[j][i]==playerSign){
                    columnCount++;
                }

                //Check Diagnotically...
                if((j+i)==rows-1){
                    if(sign[i][j]==playerSign){
                        diagCount2++;
                    }
                }

                //Check Diagnotically...
                if(j==i){
                    if(sign[i][j]==playerSign){
                        diagCount1++;
                    }
                }
            }
            if((rowCount==3 || columnCount==3 || diagCount1==3 || diagCount2==3)){
                win=true;
            }
        }
        return win;
    }

	//Movements of the Computer...
    public void computerMove(){

        //
        if(canWin('X', 'O', columns)){

            //if User Can Win The Game...
            button[this.tmpRow*rows+this.tmpColumn].setText("O");
            sign[this.tmpRow][this.tmpColumn]='O';

        }else if(canWin('O', 'X', columns)){

            //If Computer Can Win the Game...
            button[this.tmpRow*rows+this.tmpColumn].setText("O");
            sign[this.tmpRow][this.tmpColumn]='O';

        }else{

            //If Nobody Can Win the Game...
            for ( int k=0;k<(rows*columns);k++){
                        if (!isAlreadyPlaced(k/rows,k%columns)){
                            button[ k ].setText("O");
                            sign[k/rows][k%columns]='O';
                            lblMessage.setText("It's Your Turn Now..");
                            break;
                        }
            }
        }
    }

	//Check Whether Someone Can Win...
    public boolean canWin(char playerSign,char opponentSign,int count){
        boolean canWinThis=false;
        byte i,j;
        byte hCount,vCount,dCount1,dCount2;
        byte hColumn,vColumn,dColumn1,dColumn2;
        byte hRow,vRow,dRow1,dRow2;

        hColumn=vColumn=dColumn1=dColumn2=-1;
        hRow=vRow=dRow1=dRow2=-1;
        hCount=vCount=dCount1=dCount2=0;

        for (i=0; i<rows; i++) {
            hCount=vCount=0;
            hColumn=vColumn=dColumn1=dColumn2=-1;
            hRow=vRow=dRow1=dRow2=-1;

            for (j=0; j<columns;j++) {


                //Check Horizantally...
                if(sign[i][j]==playerSign){
                    hCount++;
                }else{
                    if(sign[i][j]!=opponentSign){
                        hColumn=j;
                        hRow=i;
                    }
                }

                //Check Vertically...
                if(sign[j][i]==playerSign){
                    vCount++;
                }else{
                    if(sign[j][i]!=opponentSign){
                        vColumn=j;
                        vRow=i;
                    }
                }

                //Check Diagnotically....
                if(j==i){
                    if(sign[i][j]==playerSign){
                        dCount1++;
                    }else{
                        if(sign[i][j]!=opponentSign){
                            dColumn1=j;
                            dRow1=i;
                        }
                    }
                }

                if((j+i)==rows-1){
                    if(sign[i][j]==playerSign){
                        dCount2++;
                    }else{
                        if(sign[i][j]!=opponentSign){
                            dColumn2=j;
                            dRow2=i;
                        }
                    }
                }

            }
        }

        //Check Whether there is a Chance to Win the Game...
        if(hCount==count && hColumn!=-1 && hRow!=-1){
            canWinThis=true;
            this.tmpRow=hRow;
            this.tmpColumn=hColumn;
        }else if(vCount==count && vColumn!=-1 && vRow==-1){
            canWinThis=true;
            this.tmpRow=vRow;
            this.tmpColumn=vColumn;
        }else if(dCount1==count && dColumn1!=-1 && dRow1==-1){
            canWinThis=true;
            this.tmpRow=dRow1;
            this.tmpColumn=dColumn1;
        }else if(dCount2==count && dColumn2!=-1 && dRow2==-1){
            canWinThis=true;
            this.tmpRow=dRow2;
            this.tmpColumn=dColumn2;
        }


        return canWinThis;
    }
}

    private javax.swing.JPanel gridPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblMessage;

}
