package com.vladimir.bittrexclient.config.bittrex;

import java.math.BigDecimal;

public interface Limit {
    String getTicker();
    BigDecimal getLimit();
}
