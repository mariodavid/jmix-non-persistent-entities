# Jmix Non-persistent Entities

This sample demonstrates how to use non-persistent entities in Jmix.

## Overview

The sample contains the following non-persistent entities:
* `Customer` - a non-persistent entity with a `name` attribute
* `Order` - a non-persistent entity with `orderDatea` and `amount` attributes and a reference to `Customer`

The loading of the data is performed by providing a load delegate in the corresponding View Classes. The implementation
of the loading of the data is utilising the Spring Caching abstraction.
