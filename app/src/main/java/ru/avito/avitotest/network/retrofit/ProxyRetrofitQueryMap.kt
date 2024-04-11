package ru.avito.avitotest.network.retrofit

class ProxyRetrofitQueryMap(private val original: Map<String, List<String>>) : AbstractMap<String, String>() {
    override val entries: Set<Map.Entry<String, String>>
        get() {
            return original.entries.flatMap { (key, value) -> value.map {
                java.util.AbstractMap.SimpleEntry(key, it)
            } }.toSet()
        }
}