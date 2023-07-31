package com.ardadev.presentation.component;

import com.ardadev.app.InitialTasks;
import com.ardadev.domain.entities.currency_converted.CurrencyConverted;
import com.ardadev.domain.uses_cases.currency_converted.CurrencyConvertedUseCase;
import com.ardadev.infrastructure.driven_adapter.api.currency_converted_api.CurrencyConvertedApi;

import javax.swing.*;
import java.util.List;

public class ProgressBarGettingDataProcess extends JProgressBar {
    private Boolean isTerminatedTasks;
    private CurrencyConverted currencyConverted;
    public ProgressBarGettingDataProcess() {
        this.isTerminatedTasks = false;
        setMinimum(0);
        setMaximum(100);
        setStringPainted(false);
    }

    public void executeTasks(List<Runnable> tasks) {
        SwingUtilities.invokeLater(() -> {
            for (int i = 1; i <= tasks.size(); i++) {
                double percentage = (((double) i / tasks.size()) * 100);
                tasks.get(i - 1).run();
                setValue((int) percentage);
            }
            this.isTerminatedTasks = true;
            setValue(0);
        });
    }
    public void executeTaskCurrencyConverted(String currency) {
        SwingUtilities.invokeLater(() -> {
            setValue(100);
            this.currencyConverted = new CurrencyConvertedUseCase(new CurrencyConvertedApi()).getCurrencyConverted(currency);
            this.isTerminatedTasks = true;
            setValue(0);
        });
    }

    public CurrencyConverted getCurrencyConverted() {
        return currencyConverted;
    }
    public Boolean getTerminatedTasks() {
        return isTerminatedTasks;
    }
}
