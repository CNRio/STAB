package stab.build;

import lombok.extern.log4j.Log4j;
import pl.zankowski.iextrading4j.client.IEXTradingClient;
import pl.zankowski.iextrading4j.client.rest.request.stocks.PriceRequestBuilder;

import java.math.BigDecimal;

@Log4j
public class Test {
    public static void main(String... args) {
        final IEXTradingClient iexTradingClient = IEXTradingClient.create();
        final BigDecimal quote = iexTradingClient.executeRequest(new PriceRequestBuilder()
                .withSymbol("AAPL")
                .build());
        log.info(quote);
    }
}
