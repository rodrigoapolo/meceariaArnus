package com.arnus.merceariaarnus.Utils;

public class FormatacaoCpfCnpj {
    public static String formatarCpfCnpj(String cpfCnpj, String msg){
        String newCnpj = "";
        newCnpj = cpfCnpj.replaceAll("[^0-9]", "");
        if(newCnpj.length()==11)
            return newCnpj;
        else if(newCnpj.length()==14)
            return newCnpj;

        throw new IllegalArgumentException(msg);
    }

    public static String mascaraCpf(String cpf) {
        String regex = "^(\\d{3})(\\d{3})(\\d{3})(\\d{2})$";
        String substituicao = "$1.$2.$3-$4";
        return cpf.replaceAll(regex, substituicao);
    }

    public static String mascaraCnpj(String cnpj) {
        String regex = "^(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})$";
        String substituicao = "$1.$2.$3/$4-$5";
        return cnpj.replaceAll(regex, substituicao);
    }
}
