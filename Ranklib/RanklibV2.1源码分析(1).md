##RanklibV2.1 源码分析（1）
###主框架  ciir.umass.edu.learning.Evaluator.java

```java
/**
主程序：读入命令行参数，train/test
*/
public static void main(String[] args){
	//rType2: rank的类型，可以是 MART/lambdaMART
	Evaluator e = new Evaluator(rType2[rankerType], trainMetric, testMetric);
	
	//若提供trainfile，则调用evaluate方法训练
	if(trainfile != null){
		e.evaluate(trainFile, featureDescriptionFile, foldCV);
	}else{
		e.test(savedModelFile, testFile, printIndividual);
		e.rank(savedModelFile, rankFile);
		e.score(savedModelFile, rankFile, scoreFile);
	}
}

//读取数据文件，trainFile/validateFile
//返回的对象是 List<RankList> 类型，这是封装了搜索结果列表的数据结构
public List<RankList> readInput(String inputFile){
	//FeatureManager 处理data中 featureID 到 name 的映射
	FeatureManager fm = new FeatureManager();
	List<RankList> samples = fm.read(inputFile, letor, mustHaveRelDoc);
	return samples;
}

//读取 featureDefFile
public int[] readFeature(String featureDefFile){
}

```