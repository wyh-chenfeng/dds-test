package common.annotation.datasource;



public class DynamicDataSourceHandler {
    public static final ThreadLocal<String> holder = new ThreadLocal<String>();

//    public static AtomicInteger slaveFlag = new AtomicInteger();

    public static void putDataSource(String name) {
    	  
    	// 设置读数据源和默认数据源
//    	if (DataSource.SLAVE.equals(name)) {
//    		int idx = Math.abs(slaveFlag.getAndIncrement());
//    		holder.set(name + (idx % 2 + 1));
//		} else {
//			holder.set(DataSource.MASTER);
//		}
    	
        holder.set(name);
    }

    public static String getDataSouce() {
        return holder.get();
    }
}