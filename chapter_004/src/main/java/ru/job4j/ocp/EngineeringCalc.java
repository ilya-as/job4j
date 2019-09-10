package ru.job4j.ocp;

import ru.job4j.srp.InteractCalc;

import java.io.BufferedReader;

/**
 * Инженерный калькулятор
 *
 * @author Ilya Aslamov
 * @version $Id$
 * @since 0.1
 */
public class EngineeringCalc extends InteractCalc {
    public EngineeringCalc() {
        super();
    }

    @Override
    public void fillActions() {
        super.fillActions();
        super.getActions().put("cos", (BufferedReader reader) -> super.setCalcResult(Math.cos(super.inputValue(reader))));
        super.getActions().put("sin", (BufferedReader reader) -> super.setCalcResult(Math.sin(super.inputValue(reader))));
    }

    public static void main(String[] args) {
        EngineeringCalc engCalc = new EngineeringCalc();
        engCalc.runCalc();
    }

}
