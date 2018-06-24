# STAB (Stock Transaction Assistant & Bot)

## Introduction
STAB is short for Stock Transaction Assistant & Bot. It's a platform for generating stock transaction suggestions based
on customer defined strategies. The system leverages [IEX platform](https://iextrading.com/developer/) and 
[IEX java library](https://github.com/WojciechZankowski/iextrading4) for getting data. [TA4j](https://github.com/ta4j/ta4j)
is used as strategy library.

## Requirements

### V0.1
In V0, it will focus on concept proven and offline platform creation. 
At functionality level, the system will be a support for manual transaction.

A typical use case: 
Stock trader will investigate the stocks that worth to invest, and create a "trading pool".

After that, during trading time window, the operator will 
1. look at indicators such as KDJ/MFI, 
2. analyze indicators,
3. decide whether or not to place an order with its type, and 
4. finally submit an order. The order can be either buy or sell for either type **market** or **limit**.

By using this assistant, we want to change the process to 
1. Submit a suggestion request to the tool through command line
2. Get a list of boolean suggestion for stocks in trading pool within 1 min,
3. submit a **market** order for buying or selling. 

#### The problem it solves
* On demand HIST data fetching for batch call
* Data fetching takes time. Should have the option to store HIST data locally 
* Pluggable strategies and analysis summary
* Strategy ranking for different stock (or global)

#### The problem it does not solve
* Recommendation for stock pool (trading pool)
* Limit order and targeting price
* Serve as a service with Web or UI interaction
* Buying pivot or selling pivot notification
* Auto trading
