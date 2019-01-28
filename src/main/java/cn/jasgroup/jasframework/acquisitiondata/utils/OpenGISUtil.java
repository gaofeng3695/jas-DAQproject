package cn.jasgroup.jasframework.acquisitiondata.utils;

/**
 *
 * Created by kc on 2019/1/28.
 */
public class OpenGISUtil {

    /***
     * <p>功能描述：默认的空间字段名称。</p>
     * <p> kc。</p>
     * @since JDK1.8。
     * <p>创建日期:2019年1月28日 下午2:12:10。</p>
     * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
     */
    public static String GeomFieldName = "geom";
    /***
     * <p>功能描述：空间表所用的坐标系4490（CGCS2000大地坐标系） 。</p>
     * <p> kc。</p>
     * @since JDK1.8。
     * <p>创建日期:2019年1月28日 下午2:12:10。</p>
     * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
     */
    public static int SRID = 4490;
    /***
     * <p>功能描述：创建点空间对象wkt字符串。</p>
     * <p> kc。</p>
     * @param coors 坐标数组，坐标的顺序为：经度、纬度、高程、里程
     * @param dimension 坐标维度，应该与空间表空间字段维度相同，不足补0
     * @return
     * @since JDK1.8。
     * <p>创建日期:2019年1月28日 下午2:12:10。</p>
     * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
     */
    public static String toPointWkt(double[] coors ,int dimension ){
        StringBuffer result = new StringBuffer();
        result.append("SRID=4490;");
        result.append("POINT(");
        for(int i = 0 ; i < dimension ;i++){
            if(coors.length > i){
                result.append(coors[i]);
            }else{
                result.append(0);
            }
            result.append(" ");
        }
        result.deleteCharAt(result.length() - 1);
        result.append(")");
        return result.toString();
    }
    /***
     * <p>功能描述：创建点空间对象wkt字符串,默认为4维坐标。</p>
     * <p> kc。</p>
     * @param coors 坐标数组,坐标的顺序为：经度、纬度、高程、里程
     * @return
     * @since JDK1.8。
     * <p>创建日期:2019年1月28日 下午2:12:10。</p>
     * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
     */
    public static String toPointWkt(double[] coors ){
        return toPointWkt(coors ,4);
    }
    /***
     * <p>功能描述：创建线空间对象wkt字符串 </p>
     * <p> kc。</p>
     * @param path 坐标二维数组，坐标的顺序为：经度、纬度、高程、里程
     * @param dimension 坐标维度，应该与空间表空间字段维度相同，不足补0
     * @return
     * @since JDK1.8。
     * <p>创建日期:2019年1月28日 下午2:12:10。</p>
     * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
     */
    public static String toLineWkt(double[][] path ,int dimension ){
        StringBuffer result = new StringBuffer();
        result.append("SRID=4490;");
        result.append("LINESTRING(");
        for(int j = 0 ; j < path.length ;j++){
            for(int i = 0 ; i < dimension ;i++){
                if(path[j].length > i){
                    result.append(path[j][i]);
                }else{
                    result.append(0);
                }
                result.append(" ");
            }
            result.deleteCharAt(result.length() - 1);
            result.append(",");
        }
        result.deleteCharAt(result.length() - 1);
        result.append(")");
        return result.toString();
    }
    /***
     * <p>功能描述：创建线空间对象wkt字符串,默认为4维坐标。</p>
     * <p> kc。</p>
     * @param path 坐标二维数组，坐标的顺序为：经度、纬度、高程、里程
     * @return
     * @since JDK1.8。
     * <p>创建日期:2019年1月28日 下午2:12:10。</p>
     * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
     */
    public static String toLineWkt(double[][] path  ){
        return toLineWkt(path,4);
    }
}
