package com.ardadev.presentation;

import com.ardadev.config.ImageReader;
import com.ardadev.config.ImageResizer;
import com.ardadev.app.InitialTasks;
import com.ardadev.domain.entities.country.Country;
import com.ardadev.domain.entities.currency_converted.CurrencyConverted;
import com.ardadev.domain.uses_cases.country.CountryUseCase;
import com.ardadev.domain.uses_cases.currency_converted.CurrencyConvertedUseCase;
import com.ardadev.infrastructure.driven_adapter.api.country_api.CountryApi;
import com.ardadev.infrastructure.driven_adapter.api.currency_converted_api.CurrencyConvertedApi;
import com.ardadev.presentation.component.ComboBoxCountry.CustomComboBoxModel;
import com.ardadev.presentation.component.ComboBoxCountry.CustomComboBoxRendererWithImage;
import com.ardadev.presentation.component.MenuBarApp;
import com.ardadev.presentation.component.PanelWithImage;
import com.ardadev.presentation.component.ProgressBarGettingDataProcess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class AppView extends JFrame {

    private static CardLayout mainCardLayout;
    private static JPanel mainBody;
    private static ProgressBarGettingDataProcess progressBarLoadConsults;
    private static CurrencyConverted localCurrencyConverted;
    private static List<Country> ListCountriesAccepted;
    private static Country countryIn;
    private static Country countryOut;
    public AppView(InitialTasks initialTasks) {
        progressBarLoadConsults = new ProgressBarGettingDataProcess();
        countryIn = (Country) initialTasks.getResults().get(3).get("result");
        localCurrencyConverted = (CurrencyConverted) initialTasks.getResults().get(4).get("result");

        String countryHeader = null;

        countryOut = new CountryUseCase(new CountryApi()).getCountry("us");
        if (countryOut == null) System.exit(1);

        if (countryIn != null) {
            countryHeader = countryIn.getCommonName();
        }else{
            countryIn = countryOut;
        }

        if (localCurrencyConverted == null) {
            progressBarLoadConsults.executeTaskCurrencyConverted("USD");
            localCurrencyConverted = progressBarLoadConsults.getCurrencyConverted();
            if (localCurrencyConverted == null) System.exit(1);
        }

        ListCountriesAccepted = (List<Country>) initialTasks.getResults().get(5).get("result");

        JPanel rootPanel = new JPanel(new BorderLayout());

        mainCardLayout = new CardLayout();
        mainBody = new JPanel(mainCardLayout);
        mainBody.add(WelcomeView.createWelcomePanel(), "Welcome");
        mainBody.add(CurrencyConverterView.createCurrencyConverterPanel(), "Currency Converter");
        mainBody.add(UnitsConverterView.createUnitConverterPanel(), "Units Converter");
        mainCardLayout.show(mainBody, "Welcome");

        JLabel footer = new JLabel("© 2023 ArdaDev, All rights reserved.");
        footer.setHorizontalAlignment(SwingConstants.CENTER);
        footer.setBorder(BorderFactory.createEmptyBorder(15,0,0,0));

        rootPanel.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));

        setResizable(false);
        setJMenuBar(new MenuBarApp(mainCardLayout, mainBody, progressBarLoadConsults));
        setTitle("Currency Converter" + (countryHeader == null ? "" : ": " + countryHeader));
        setIconImage(ImageReader.readLocalImage("assets/favicon.png"));

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(0,0,400, 400);
        setLocationRelativeTo(null);

        rootPanel.add(createHeader(), BorderLayout.NORTH);
        rootPanel.add(mainBody, BorderLayout.CENTER);
        rootPanel.add(footer, BorderLayout.SOUTH);
        getContentPane().add(rootPanel);
    }

    public AppView() {
        setContentPane(new JPanel());
        ListCountriesAccepted = new CountryUseCase(new CountryApi()).getListCountries("usd");
        JComboBox<Country> c = new JComboBox<>(new CustomComboBoxModel(ListCountriesAccepted));
        c.setRenderer(new CustomComboBoxRendererWithImage());

        getContentPane().add(c);

        Country as = new CountryUseCase(new CountryApi()).getCountry("us");
        CustomComboBoxModel f = (CustomComboBoxModel) c.getModel();
        c.setSelectedIndex(f.getIndexCountry(as));

    }

    private JPanel createHeader() {
        JPanel panelHeader = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));

        JLabel title = new JLabel(new ImageIcon(Objects.requireNonNull(ImageResizer.resizedByWidthLocal("assets/CurrencyConverter.png", 160))));

        progressBarLoadConsults.setBorder(BorderFactory.createMatteBorder(0, 70,0,0, Color.white));
        progressBarLoadConsults.setPreferredSize(new Dimension(200, 30));
        panelHeader.setBackground(Color.white);
        panelHeader.add(title);
        panelHeader.add(progressBarLoadConsults);
        progressBarLoadConsults.setVisible(false);

        return panelHeader;
    }

    public void showApp() {
        setVisible(true);
    }

    private static class WelcomeView {
        private static JPanel createWelcomePanel() {
            PanelWithImage body = new PanelWithImage(ImageReader.readLocalImage("assets/backgroundFlags.png"));
            BorderLayout borderLayoutBody = new BorderLayout();
            body.setLayout(borderLayoutBody);

            JPanel panelMainMenu = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 10));
            panelMainMenu.setOpaque(false);
            panelMainMenu.setPreferredSize(new Dimension(180, 100));
            panelMainMenu.setBorder(BorderFactory.createTitledBorder("Main Menu"));
            JButton buttonCurrencyConverter = new JButton("Currency Converter");
            buttonCurrencyConverter.addActionListener((e) -> {
                mainCardLayout.show(mainBody, "Currency Converter");
                progressBarLoadConsults.setVisible(true);
            });
            JButton buttonUnitsConverter = new JButton("Units Converter");
            buttonUnitsConverter.addActionListener((e) -> {
                mainCardLayout.show(mainBody, "Units Converter");
                progressBarLoadConsults.setVisible(false);
            });
            // The button of Units Converter is disabled for it's in develop progress
            buttonUnitsConverter.setEnabled(false);
            JButton buttonExit = new JButton("Exit");
            buttonExit.addActionListener((e) -> System.exit(1));

            panelMainMenu.add(buttonCurrencyConverter);
            panelMainMenu.add(buttonUnitsConverter);
            panelMainMenu.add(buttonExit);

            JLabel space1 = new JLabel(" ");
            space1.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
            JLabel space2 = new JLabel(" ");
            space2.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));

            body.add(space1, BorderLayout.NORTH);
            body.add(panelMainMenu, BorderLayout.WEST);
            body.add(space2, BorderLayout.SOUTH);

            return body;
        }
    }

    private static class CurrencyConverterView {
        private static JPanel createCurrencyConverterPanel() {
            JPanel body = new JPanel(new GridLayout(2, 1, 0,5));

            JPanel panelResults = new JPanel(new GridLayout(1, 2));

            JPanel panelTexts = new JPanel(new GridLayout(3,1));
            String stringLabel1 = "1 " + "(" + countryIn.getCurrencySymbol() + ") " + countryIn.getCurrencyName();
            JLabel label1 = new JLabel(stringLabel1);
            label1.setToolTipText(stringLabel1);
            label1.setFont(new Font(Font.DIALOG, Font.BOLD, 13));
            label1.setHorizontalAlignment(SwingConstants.CENTER);
            String stringLabel2 = localCurrencyConverted.getConversion_rates().get(countryOut.getCurrencyCode()) + "(" + countryOut.getCurrencySymbol() + ") " + countryOut.getCurrencyName();
            JLabel label2 = new JLabel(stringLabel2);
            label2.setToolTipText(stringLabel2);
            label2.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
            label2.setHorizontalAlignment(SwingConstants.CENTER);
            JLabel label3 = new JLabel("[Last Update] " + localCurrencyConverted.getTime_last_update_utc());
            panelTexts.add(label1);
            panelTexts.add(label2);
            panelTexts.add(label3);

            JPanel panelFlags = new JPanel(new GridLayout(2,1));
            JLabel imageFlagIn = new JLabel(new ImageIcon(Objects.requireNonNull(ImageResizer.resizeByWidthURL(countryIn.getFlagPng(), 75))));
            imageFlagIn.setToolTipText(countryIn.getFlagInfo());
            JLabel imageFlagOut = new JLabel(new ImageIcon(Objects.requireNonNull(ImageResizer.resizeByWidthURL(countryOut.getFlagPng(), 75))));
            imageFlagOut.setToolTipText(countryOut.getFlagInfo());
            panelFlags.add(imageFlagIn);
            panelFlags.add(imageFlagOut);

            panelResults.add(panelTexts);
            panelResults.add(panelFlags);

            JPanel panelCurrency = new JPanel(new GridLayout(2, 1, 0, 10));
            List<Country> listCountriesForCurrencies = getListCountriesWithCurrencies(
                    ListCountriesAccepted,
                    localCurrencyConverted);

            JPanel panelCurrencyIn = new JPanel(new GridLayout(1, 2, 5,0));
            JTextField currencyIn = new JTextField("1");
            currencyIn.setFont(new Font(null, Font.BOLD, 15));
            currencyIn.setHorizontalAlignment(JTextField.RIGHT);
            CustomComboBoxModel modelNameCurrencyIn = new CustomComboBoxModel(listCountriesForCurrencies);
            JComboBox<Country> nameCurrencyIn = new JComboBox<>(modelNameCurrencyIn);
            nameCurrencyIn.setRenderer(new CustomComboBoxRendererWithImage());

            currencyIn.setBorder(BorderFactory.createEmptyBorder(5,5,5, 5));
            nameCurrencyIn.setBorder(BorderFactory.createEmptyBorder(5,5,5, 5));
            panelCurrencyIn.add(currencyIn);
            panelCurrencyIn.add(nameCurrencyIn);
            panelCurrencyIn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));

            JPanel panelCurrencyOut = new JPanel(new GridLayout(1, 2, 5,0));
            JTextField currencyOut = new JTextField(Double.toString(localCurrencyConverted.getConversion_rates().get(countryOut.getCurrencyCode())));
            currencyOut.setFont(new Font(null, Font.BOLD, 15));
            currencyOut.setHorizontalAlignment(JTextField.RIGHT);
            currencyOut.setEnabled(false);
            CustomComboBoxModel modelNameCurrencyOut = new CustomComboBoxModel(listCountriesForCurrencies);
            JComboBox<Country> nameCurrencyOut = new JComboBox<>(modelNameCurrencyOut);
            nameCurrencyOut.setRenderer(new CustomComboBoxRendererWithImage());

            currencyOut.setBorder(BorderFactory.createEmptyBorder(5,5,5, 5));
            nameCurrencyOut.setBorder(BorderFactory.createEmptyBorder(5,5,5, 5));
            panelCurrencyOut.add(currencyOut);
            panelCurrencyOut.add(nameCurrencyOut);
            panelCurrencyOut.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));

            panelCurrency.add(panelCurrencyIn);
            panelCurrency.add(panelCurrencyOut);

            body.add(panelResults);
            body.add(panelCurrency);

            nameCurrencyIn.setSelectedIndex(modelNameCurrencyIn.getIndexCountry(countryIn));
            nameCurrencyOut.setSelectedIndex(modelNameCurrencyIn.getIndexCountry(countryOut));

            nameCurrencyIn.addActionListener(e -> {
                JComboBox<Country> jComboBox = (JComboBox<Country>) e.getSource();
                Country countrySelected = (Country) jComboBox.getSelectedItem();

                List<Runnable> tasks = new ArrayList<>();
                tasks.add(() -> {
                    SwingUtilities.invokeLater(() -> {
                        countryIn = countrySelected;
                        imageFlagIn.setIcon(new ImageIcon(Objects.requireNonNull(ImageResizer.resizeByWidthURL(countrySelected.getFlagPng(), 75))));
                        imageFlagIn.setToolTipText(countrySelected.getFlagInfo());
                        String taskStringLabel1 = "1 " + "(" + countrySelected.getCurrencySymbol() + ") " + countrySelected.getCurrencyName();
                        label1.setText(taskStringLabel1);
                        label1.setToolTipText(taskStringLabel1);
                        String taskStringLabel2 = localCurrencyConverted.getConversion_rates().get(countryOut.getCurrencyCode()) + " (" + countryOut.getCurrencySymbol() + ") " + countryOut.getCurrencyName();
                        label2.setText(taskStringLabel2);
                        label2.setToolTipText(taskStringLabel2);
                    });
                });
                tasks.add(() -> {
                    assert countrySelected != null;
                    localCurrencyConverted = new CurrencyConvertedUseCase(
                            new CurrencyConvertedApi()
                    ).getCurrencyConverted(countrySelected.getCurrencyCode());
                });
                tasks.add(() -> {
                    SwingUtilities.invokeLater(() -> {
                        Country countryOut = (Country) nameCurrencyOut.getSelectedItem();
                        assert countryOut != null;
                        double currencyChange = localCurrencyConverted.getConversion_rates().get(countryOut.getCurrencyCode());
                        double change = Double.parseDouble(currencyIn.getText()) * currencyChange;
                        currencyOut.setText(Double.toString(change));
                    });
                });
                progressBarLoadConsults.executeTasks(tasks);
            });
            nameCurrencyOut.addActionListener(e -> {
                JComboBox<Country> jComboBox = (JComboBox<Country>) e.getSource();
                Country countrySelected = (Country) jComboBox.getSelectedItem();

                List<Runnable> tasks = new ArrayList<>();
                tasks.add(() -> {
                    SwingUtilities.invokeLater(() -> {
                        countryOut = countrySelected;
                        imageFlagOut.setIcon(new ImageIcon(Objects.requireNonNull(ImageResizer.resizeByWidthURL(countrySelected.getFlagPng(), 75))));
                        imageFlagOut.setToolTipText(countrySelected.getFlagInfo());
                        String taskStringLabel2 = localCurrencyConverted.getConversion_rates().get(countrySelected.getCurrencyCode()) + " (" + countrySelected.getCurrencySymbol() + ") " + countrySelected.getCurrencyName();
                        label2.setText(taskStringLabel2);
                        label2.setToolTipText(taskStringLabel2);
                    });
                });
                tasks.add(() -> {
                    SwingUtilities.invokeLater(() -> {
                        Country countryOut = (Country) nameCurrencyOut.getSelectedItem();
                        assert countryOut != null;
                        double currencyChange = localCurrencyConverted.getConversion_rates().get(countryOut.getCurrencyCode());
                        double change = Double.parseDouble(currencyIn.getText()) * currencyChange;
                        currencyOut.setText(Double.toString(change));
                    });
                });
                progressBarLoadConsults.executeTasks(tasks);
            });

            return body;
        }
    }

    private static class UnitsConverterView {

        private static CardLayout cardLayoutUnitsConverter;
        private static JPanel createUnitConverterPanel() {
            JPanel panelRoot = new JPanel(new BorderLayout());

            cardLayoutUnitsConverter = new CardLayout();
            JPanel body = new JPanel(cardLayoutUnitsConverter);

            JScrollPane ScrollUnitType = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            ScrollUnitType.setBorder(BorderFactory.createEmptyBorder(5,0,15,0));
            JPanel panelUnitType = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JButton button1 = new JButton("Superficie");
            JButton button2 = new JButton("Longitud");
            JButton button3 = new JButton("Temperatura");
            JButton button4 = new JButton("Volumen");
            JButton button5 = new JButton("Masa");
            JButton button6 = new JButton("Datos");
            JButton button7 = new JButton("Velocidad");
            JButton button8 = new JButton("Tiempo");
            panelUnitType.add(button1);
            panelUnitType.add(button2);
            panelUnitType.add(button3);
            panelUnitType.add(button4);
            panelUnitType.add(button5);
            panelUnitType.add(button6);
            panelUnitType.add(button7);
            panelUnitType.add(button8);
            ScrollUnitType.setViewportView(panelUnitType);

            JPanel panelResults = new JPanel(new GridLayout(2,1, 5, 10));

            String[] items = {"Milímetros", "Centímetros", "Metros", "Kilómetros", "Pulgadas", "Pies", "Yardas", "Millas", "Millas náuticas", "Miles"};
            panelResults.add(createPanelResult(items));
            panelResults.add(createPanelResult(items));
            body.add(panelResults, "Longitud");
            cardLayoutUnitsConverter.show(body, "Longitud");

            panelRoot.add(ScrollUnitType, BorderLayout.NORTH);
            panelRoot.add(body, BorderLayout.CENTER);

            return panelRoot;
        }

        private static JPanel createPanelResult(String[] items) {
            JPanel panelResult = new JPanel(new BorderLayout());

            panelResult.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
            JPanel panelResultComboBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JPanel panelResultIn = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JComboBox<String> comboBox = new JComboBox<>(items);
            comboBox.setPreferredSize(new Dimension(130,25));
            JLabel symbolUnit = new JLabel(comboBox.getSelectedItem().toString());

            comboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    symbolUnit.setText(comboBox.getSelectedItem().toString());
                }
            });

            JTextField textField = new JTextField();
            textField.setPreferredSize(new Dimension(170,30));
            textField.setHorizontalAlignment(JTextField.RIGHT);
            textField.setFont(new Font(Font.MONOSPACED, Font.BOLD, 17));

            panelResultIn.add(textField);
            panelResultIn.add(symbolUnit);
            panelResultComboBox.add(comboBox);
            panelResult.add(panelResultComboBox, BorderLayout.NORTH);
            panelResult.add(panelResultIn, BorderLayout.SOUTH);

            return panelResult;
        }
    }

    private static List<Country> getListCountriesWithCurrencies(List<Country> listAllCountries, CurrencyConverted localCurrencyConverted) {
        List<Country> listCountries = listAllCountries;
        List<String> listAllCurrencies = new ArrayList<>(
                localCurrencyConverted.getConversion_rates().keySet()
        );
        List<Country> listCountriesWithCurrencies = new ArrayList<>();

        for (String currencyCode: listAllCurrencies) {
            List<Country> listAux = new ArrayList<>();
            for (Country country: listCountries) {
                if (country.getCurrencies() != null) {
                    if (Objects.equals(country.getCurrencyCode(), currencyCode)) {
                        listCountriesWithCurrencies.add(country);
                    }
                    listAux.add(country);
                }
            }
            listCountries = listAux;
        }
        //listCountriesWithCurrencies.sort((o1, o2) -> o1.compareTo(o2));
        listCountriesWithCurrencies.sort(Country::compareTo);

        return listCountriesWithCurrencies;
    }
}
