package calculadora;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    
    private double numero1 = 0;
    private String operador = "";
    private boolean novoNumero = true;

    @Override
    
    // Inicializa e configura a interface da calculadora
    public void start(Stage stage) {

        // Configura o layout principal
        VBox root = new VBox(10);
        root.setStyle("-fx-padding: 15 15 15 15;");

        // Configura o display
        Label display = new Label("0");
        display.setPrefHeight(100);
        display.setMaxWidth(Double.MAX_VALUE);
        display.setAlignment(Pos.CENTER_RIGHT);
        display.getStyleClass().add("display");

        // Configura o grid de botões
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

    
        int cols = 4;
        int rows = 5; 
        double btnWidth = 75;
        double btnHeight = 70;

        // Define restrições de colunas
        for (int c = 0; c < cols; c++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setMinWidth(btnWidth);
            cc.setPrefWidth(btnWidth);
            cc.setMaxWidth(btnWidth);
            cc.setHgrow(Priority.NEVER);
            grid.getColumnConstraints().add(cc);
        }

        // Define restrições de linhas
        for (int r = 0; r < rows; r++) {
            RowConstraints rc = new RowConstraints();
            rc.setMinHeight(btnHeight);
            rc.setPrefHeight(btnHeight);
            rc.setMaxHeight(btnHeight);
            rc.setVgrow(Priority.NEVER);
            grid.getRowConstraints().add(rc);
        }

        // Adiciona componentes ao layout
        root.getChildren().addAll(display, grid);

        // Define configuração para operadores
        java.util.function.BiConsumer<Button, String> configurarOperador = (botao, op) -> {
            botao.getStyleClass().add("button-operador");
            botao.setOnAction(e -> {
                
                if (novoNumero && !operador.isEmpty()) {
                    operador = op;
                    return;
                }

                try {
                    numero1 = Double.parseDouble(display.getText().replace(",", "."));
                } catch (NumberFormatException ex) {
                    numero1 = 0;
                }
                operador = op;
                novoNumero = true;
            });
        };


        // Botão CE
        criarBotao(grid, "CE", 0, 0, "button-operador", e -> {
            display.setText("0");
            novoNumero = true;
        });

        // Botão backspace
        criarBotao(grid, "⌫", 1, 0, "button-operador", e -> {
            String txt = display.getText();
            if (txt.equals("Erro")) {
                display.setText("0");
                novoNumero = true;
                return;
            }

            if (txt.length() > 1) {
                display.setText(txt.substring(0, txt.length() - 1));
            } else {
                display.setText("0");
                novoNumero = true;
            }
        });

        
        // Botão dividir
        Button dividir = criarBotao(grid, "÷", 2, 0);
        configurarOperador.accept(dividir, "÷");

        // Botão multiplicar
        Button multiplicar = criarBotao(grid, "×", 3, 0);
        configurarOperador.accept(multiplicar, "×");

        int numero = 9;
        
        // Loop para números 9-1
        for (int linha = 1; linha <= 3; linha++) {
            for (int coluna = 0; coluna < 3; coluna++) {
                int n = numero--;
                if (n <= 0) break;
                criarBotao(grid, String.valueOf(n), coluna, linha, null, e -> {
                    String txt = display.getText();
                    if (novoNumero || txt.equals("0") || txt.equals("Erro")) {
                        display.setText(String.valueOf(n));
                        novoNumero = false;
                    } else {
                        display.setText(display.getText() + n);
                    }
                });
            }
        }

        // Botão menos
        Button menos = criarBotao(grid, "-", 3, 1);
        configurarOperador.accept(menos, "-");

        // Botão mais
        Button mais = criarBotao(grid, "+", 3, 2);
        configurarOperador.accept(mais, "+");


        // Botão C
        criarBotao(grid, "C", 0, 4, "button-clear", e -> {
            display.setText("0");
            numero1 = 0;
            operador = "";
            novoNumero = true;
        });

        // Botão 0
        criarBotao(grid, "0", 1, 4, null, e -> {
            String txt = display.getText();
            if (novoNumero || txt.equals("0") || txt.equals("Erro")) {
                
                display.setText("0");
                novoNumero = false;
            } else {
                display.setText(display.getText() + "0");
            }
        });

        // Botão vírgula
        criarBotao(grid, ",", 2, 4, null, e -> {
            String txt = display.getText();
            if (txt.equals("Erro")) {
                display.setText("0,");
                novoNumero = false;
                return;
            }

            if (novoNumero) {
                display.setText("0,");
                novoNumero = false;
            } else if (!txt.contains(",")) {
                display.setText(txt + ",");
            }
        });


        // Botão igual
        Button igual = criarBotao(grid, "=", 3, 3, "button-igual", e -> {

            String txt = display.getText();
            if (txt.equals("Erro") || operador.isEmpty()) {
                novoNumero = true;
                return;
            }

            double numero2;
            try {
                numero2 = Double.parseDouble(txt.replace(",", "."));
            } catch (NumberFormatException ex) {
                display.setText("Erro");
                novoNumero = true;
                operador = "";
                return;
            }

            double resultado = 0;

            switch (operador) {
                case "+": resultado = numero1 + numero2; break;
                case "-": resultado = numero1 - numero2; break;
                case "×": resultado = numero1 * numero2; break;
                case "÷":
                    if (numero2 != 0)
                        resultado = numero1 / numero2;
                    else {
                        display.setText("Erro");
                        novoNumero = true;
                        operador = "";
                        return;
                    }
                    break;
                default:
                    novoNumero = true;
                    operador = "";
                    return;
            }

            String texto = (resultado % 1 == 0)
                    ? String.valueOf((long) resultado)
                    : String.valueOf(resultado);

            display.setText(texto.replace(".", ","));
            
            numero1 = resultado;
            operador = "";
            novoNumero = true;

        }, 1, 2);
        igual.setPrefHeight(70 * 2 + 10 + 5);

        // Configura cena e palco
        Scene scene = new Scene(root);                 
        scene.getStylesheets().add("CSS/style.css");

        stage.setTitle("Calculadora");
        stage.setScene(scene);
        stage.sizeToScene();                       
        stage.setResizable(false);
        stage.show();
    }

    // Cria botão padrão
    private Button criarBotao(GridPane grid, String texto, int col, int row) {
        return criarBotao(grid, texto, col, row, null, null, 1, 1);
    }

    // Cria botão com classe e ação
    private Button criarBotao(GridPane grid, String texto, int col, int row,
                              String classe, javafx.event.EventHandler<javafx.event.ActionEvent> acao) {
        return criarBotao(grid, texto, col, row, classe, acao, 1, 1);
    }

    // Cria botão com colspan
    private Button criarBotao(GridPane grid, String texto, int col, int row,
                              String classe, javafx.event.EventHandler<javafx.event.ActionEvent> acao,
                              int colspan) {
        return criarBotao(grid, texto, col, row, classe, acao, colspan, 1);
    }

   
    // Cria botão com colspan e rowspan
    private Button criarBotao(GridPane grid, String texto, int col, int row,
                              String classe, javafx.event.EventHandler<javafx.event.ActionEvent> acao,
                              int colspan, int rowspan) {

        Button btn = new Button(texto);
        btn.setPrefSize(75, 70);

        if (classe != null)
            btn.getStyleClass().add(classe);

        if (acao != null)
            btn.setOnAction(acao);

        grid.add(btn, col, row, colspan, rowspan);

        return btn;
    }

    // Ponto de entrada da aplicação
    public static void main(String[] args) {
        launch();
    }
}