import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ComprehensiveSwingApp extends JFrame {
    private JLabel resultLabel;
    private JList<String> languageList;
    private JList<String> frameworkList;
    private JComboBox<String> programmingComboBox;
    private JRadioButton maleRadio;
    private JRadioButton femaleRadio;

    public ComprehensiveSwingApp() {
        setTitle("Comprehensive Swing Application");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JToolBar toolbar = createToolbar();
        add(toolbar, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        resultLabel = new JLabel("Select options to see results");
        resultLabel.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(resultLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(new JLabel("Languages:"), gbc);

        DefaultListModel<String> languageModel = new DefaultListModel<>();
        languageModel.addElement("C");
        languageModel.addElement("C++");
        languageModel.addElement("Java");
        languageModel.addElement("PHP");
        languageList = new JList<>(languageModel);
        languageList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        gbc.gridy = 2;
        mainPanel.add(new JScrollPane(languageList), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(new JLabel("Frameworks:"), gbc);

        DefaultListModel<String> frameworkModel = new DefaultListModel<>();
        frameworkModel.addElement("Struts");
        frameworkModel.addElement("Spring");
        frameworkModel.addElement("Hibernate");
        frameworkList = new JList<>(frameworkModel);
        frameworkList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        gbc.gridy = 2;
        mainPanel.add(new JScrollPane(frameworkList), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(new JLabel("Select Language:"), gbc);

        String[] comboLanguages = {"Python", "JavaScript", "Ruby", "Go", "Rust"};
        programmingComboBox = new JComboBox<>(comboLanguages);
        gbc.gridx = 1;
        mainPanel.add(programmingComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        JPanel radioPanel = new JPanel();
        radioPanel.add(new JLabel("Gender: "));

        maleRadio = new JRadioButton("Male");
        femaleRadio = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        radioPanel.add(maleRadio);
        radioPanel.add(femaleRadio);
        mainPanel.add(radioPanel, gbc);

        gbc.gridy = 5;
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateResults();
            }
        });
        mainPanel.add(submitButton, gbc);

        add(mainPanel, BorderLayout.CENTER);
    }

    private JToolBar createToolbar() {
        JToolBar toolbar = new JToolBar();
        toolbar.setRollover(true);

        JButton fileButton = new JButton("File");
        JButton editButton = new JButton("Edit");
        JComboBox<String> optionsCombo = new JComboBox<>(new String[]{"Option 1", "Option 2", "Option 3"});

        toolbar.add(fileButton);
        toolbar.addSeparator();
        toolbar.add(editButton);
        toolbar.add(optionsCombo);

        return toolbar;
    }

    private void updateResults() {
        StringBuilder result = new StringBuilder("Results:\n");

        java.util.List<String> selectedLanguages = languageList.getSelectedValuesList();
        result.append("Languages: ").append(String.join(", ", selectedLanguages)).append("\n");

        java.util.List<String> selectedFrameworks = frameworkList.getSelectedValuesList();
        result.append("Frameworks: ").append(String.join(", ", selectedFrameworks)).append("\n");

        result.append("Selected Combo Language: ").append(programmingComboBox.getSelectedItem()).append("\n");

        if (maleRadio.isSelected()) {
            result.append("Gender: Male\n");
        } else if (femaleRadio.isSelected()) {
            result.append("Gender: Female\n");
        } else {
            result.append("Gender: Not selected\n");
        }

        resultLabel.setText("<html>" + result.toString().replaceAll("\n", "<br>") + "</html>");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ComprehensiveSwingApp().setVisible(true);
            }
        });
    }
}