package org.example.securiy;

import org.example.entity.Account;

public class SecurityUtils {
    private static Account account = null;

    public static Account getAccount() {
        return account;
    }

    public static void setAccount(Account account) {
        SecurityUtils.account = account;
    }
}
