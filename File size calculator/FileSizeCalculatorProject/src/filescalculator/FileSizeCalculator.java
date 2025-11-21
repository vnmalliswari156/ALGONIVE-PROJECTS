package filescalculator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.DecimalFormat;

import org.apache.pdfbox.pdmodel.PDDocument;        // <-- Added
import org.apache.pdfbox.text.PDFTextStripper;     // <-- Added

public class FileSizeCalculator extends JFrame {

    private JTextArea textArea;
    private JLabel lblFileName, lblFileSize, lblWordCount, lblCharCount, lblLongestWord, lblAvgWord;
    private JButton btnChoose, btnClear, btnExportText, btnExportPdf;
    private File currentFile = null;

    // ---------------------------- CONSTRUCTOR ----------------------------
    public FileSizeCalculator() {

        setTitle("File Size Calculator");
        setSize(950, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        // ---------------- HEADER ----------------
        JPanel header = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(40, 0, 80),
                        0, getHeight(), new Color(100, 0, 160));
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        header.setPreferredSize(new Dimension(100, 80));
        JLabel headLabel = new JLabel("FILE SIZE CALCULATOR");
        headLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        headLabel.setForeground(Color.WHITE);
        header.add(headLabel);

        add(header, BorderLayout.NORTH);

        // ---------------- MAIN CENTER AREA ----------------
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(25, 0, 50));
        add(mainPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ---------------- INFO PANEL ----------------
        JPanel panelInfo = createGlassPanel(20);
        panelInfo.setLayout(new GridLayout(3, 1, 5, 5));

        lblFileName = createLabel("File: None");
        lblFileSize = createLabel("Size: 0 KB");

        panelInfo.add(lblFileName);
        panelInfo.add(lblFileSize);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        mainPanel.add(panelInfo, gbc);

        // ---------------- STATS PANEL ----------------
        JPanel panelStats = createGlassPanel(20);
        panelStats.setLayout(new GridLayout(4, 1, 5, 5));

        lblWordCount = createLabel("Word Count: 0");
        lblCharCount = createLabel("Character Count: 0");
        lblLongestWord = createLabel("Longest Word: None");
        lblAvgWord = createLabel("Avg Word Length: 0");

        panelStats.add(lblWordCount);
        panelStats.add(lblCharCount);
        panelStats.add(lblLongestWord);
        panelStats.add(lblAvgWord);

        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(panelStats, gbc);

        // ---------------- TEXT AREA ----------------
        textArea = new JTextArea();
        textArea.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        textArea.setForeground(Color.WHITE);
        textArea.setBackground(new Color(55, 0, 100));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(420, 520));

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(scrollPane, gbc);

        // ---------------- BUTTON PANEL ----------------
        JPanel btnPanel = new JPanel(new GridLayout(2, 2, 15, 15));
        btnPanel.setOpaque(false);

        btnChoose = styledButton("Choose File");
        btnClear = styledButton("Clear");
        btnExportText = styledButton("Export Text");
        btnExportPdf = styledButton("Export Stats (PDF)");

        btnPanel.add(btnChoose);
        btnPanel.add(btnClear);
        btnPanel.add(btnExportText);
        btnPanel.add(btnExportPdf);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(btnPanel, gbc);

        // ---------------- BUTTON ACTIONS ----------------
        btnChoose.addActionListener(e -> chooseFile());
        btnClear.addActionListener(e -> clearAll());
        btnExportText.addActionListener(e -> exportText());
        btnExportPdf.addActionListener(e -> exportPdf());
    }

    // ---------------------------- HELPERS ----------------------------
    private JLabel createLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setForeground(Color.WHITE);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 16));
        return lbl;
    }

    private JPanel createGlassPanel(int radius) {
        return new JPanel() {
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(new Color(255, 255, 255, 40));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
                g2.dispose();
            }

            public boolean isOpaque() { return false; }
        };
    }

    private JButton styledButton(String text) {
        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setBackground(new Color(70, 0, 140));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBorder(new EmptyBorder(10, 20, 10, 20));
        return btn;
    }

    // ---------------------------- LOGIC ----------------------------
    private void chooseFile() {
        JFileChooser fc = new JFileChooser();
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            currentFile = fc.getSelectedFile();
            lblFileName.setText("File: " + currentFile.getName());
            loadFileData();
        }
    }

    // ---------------------------- FIXED PDF + TXT LOADING ----------------------------
    private void loadFileData() {
        try {
            String fileName = currentFile.getName().toLowerCase();
            String content = "";

            // ----- If PDF -----
            if (fileName.endsWith(".pdf")) {
                PDDocument doc = PDDocument.load(currentFile);
                PDFTextStripper stripper = new PDFTextStripper();
                content = stripper.getText(doc);
                doc.close();
            }
            // ----- If TXT -----
            else if (fileName.endsWith(".txt")) {
                content = Files.readString(currentFile.toPath(), StandardCharsets.UTF_8);
            }
            // ----- Unsupported -----
            else {
                JOptionPane.showMessageDialog(this,
                        "Unsupported file type! Only PDF and TXT are allowed.");
                return;
            }

            textArea.setText(content);

            long size = Files.size(currentFile.toPath());
            lblFileSize.setText("Size: " + (size / 1024) + " KB");

            String[] words = content.trim().isEmpty() ? new String[]{} : content.split("\\s+");

            lblWordCount.setText("Word Count: " + words.length);
            lblCharCount.setText("Character Count: " + content.length());

            String longest = "";
            for (String w : words)
                if (w.length() > longest.length()) longest = w;

            lblLongestWord.setText("Longest Word: " + longest);
            lblAvgWord.setText("Avg Word Length: " +
                    (words.length == 0 ? 0 :
                            new DecimalFormat("#.##")
                                    .format(content.length() / (double) words.length)));

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error loading file! The PDF may be protected.");
        }
    }

    private void clearAll() {
        textArea.setText("");
        lblFileName.setText("File: None");
        lblFileSize.setText("Size: 0 KB");
        lblWordCount.setText("Word Count: 0");
        lblCharCount.setText("Character Count: 0");
        lblLongestWord.setText("Longest Word: None");
        lblAvgWord.setText("Avg Word Length: 0");
    }

    private void exportText() {
        try {
            File file = new File("exported_text.txt");
            Files.writeString(file.toPath(), textArea.getText());
            JOptionPane.showMessageDialog(this, "Text exported successfully!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error exporting!");
        }
    }

    private void exportPdf() {
        JOptionPane.showMessageDialog(this, "PDF export coming soon!");
    }

    public static void main(String[] args) {
        new FileSizeCalculator().setVisible(true);
    }
}
