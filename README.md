This sample demonstrates exception while deserializing a json payload writen onto disk using Jackson Polymorphic deserializer using default typing. When we use kotlin data classes to hold the payload and if we have a setterless fields jackson libray is not able to deserialize and gives exception

Exception in thread "main" com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Problem deserializing 'setterless' property ("synopsis"): no way to handle typed deser with setterless yet
