# STAB (Stock Transaction Assistant & Bot)

## Introduction
STAB is short for Stock Transaction Assistant & Bot. It's a platform for generating stock transaction suggestions based
on customer defined strategies. The system leverages [https://iextrading.com/developer/ IEX platform] and 
[https://github.com/WojciechZankowski/iextrading4j IEX java library].

## Requirements

### Assistant
In Milestone 1, we want to implement transaction assistant, which support

* On demand HIST data fetching for batch call
* Data fetching takes time. Should have the option to store HIST data locally 
* Strategies calculation
* Pluggable strategies
* Strategy ranking for different stock (or global)

Bot will be supported in Milestone 2