package stab.data;

import com.sun.istack.internal.NotNull;

@lombok.Data
public class IexData implements Data {

    /**
     * Symbol is the short name of a stock (e.g. AMZN is the symbol of Amazon)
     */
    @NotNull String symbol;

}
