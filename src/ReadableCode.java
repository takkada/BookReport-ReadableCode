import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;


/**
 * 読みやすいコードについて考える。
 *
 * @author r-takada
 */
public class ReadableCode {

	// 0. 最初に
	//
	// プログラマの仕事はソースを「書く」事である。
	// しかし、大半の時間はソースを「読む」事が多い。
	// 正しく動けば問題ない、という考えは間違ってはいないが、保守や障害発見のためにも、ソースは読みやすくした方がよい。
	// 「Listパラメータの中にある商品データを画面に出力する」メソッドを見ながら、
	// 読みやすいコードとは何かを考える。

	// 1. ソースフォーマット
	private static void getData(List<ProductBean> list) {

		list = sortOrder(list);

		// リスト逆順にイテレートする
		for(ListIterator<ProductBean>i =list.listIterator(list.size());i.hasPrevious();) {
		ProductBean bean1=(ProductBean)i.previous();
		// 最低価格以上の項目を出力する
		if(Integer.parseInt(PRICE_LIST.split(":")[0].trim())<= bean1.getPrice() )
	{
		// 商品名がNULLの場合、「なし」を設定する
		if(null ==bean1.getOfficial() ) bean1.setOfficial(PRODUCT_NAME_NONE);
			//表示
			displayData(bean1);
			}
		}
	}

	// 正直このままでは読みづらい。
	// ・インデントがあっていない
	// ・for文、if文の間隔が不揃い
	// などが原因であるため、ソースフォーマットを行う。
	// また、if文の括弧を記述がないので追加する。
	// 括弧がない場合、最初の文だけが実行される事になるが、処理を追加する場合に括弧がないと、
	// 障害が発生する恐れがある。

	// 2.「3.7.ユーザの期待に合わせる」
	private static void getData2(List<ProductBean> list) {

		list = sortOrder(list);

		// リスト逆順にイテレートする
		for (ListIterator<ProductBean> i = list.listIterator(list.size()); i
				.hasPrevious();) {
			ProductBean bean1 = (ProductBean) i.previous();

			// 最低価格以上の項目を出力する
			if (Integer.parseInt(PRICE_LIST.split(":")[0].trim()) <= bean1.getPrice()) {

				// 商品名がNULLの場合、「なし」を設定する
				if (null == bean1.getOfficial()) {
					bean1.setOfficial(PRODUCT_NAME_NONE);
				}

				// 表示
				displayData(bean1);
			}
		}
	}

	// メソッド名がget～となっている。
	// 多くの人は、getで始まるメソッドは、メンバの値を返すだけの「軽量プロセサ」だと判断する。
	// そのため、このメソッドで行う「商品データを出力する」という意味の「displayPriceData」に変更する。

	// 3. 「2.4.名前に情報を追加する」
	private static void displayPriceData3(List<ProductBean> list) {

		list = sortOrder(list);

		// リスト逆順にイテレートする
		for (ListIterator<ProductBean> iterator = list.listIterator(list.size()); iterator
				.hasPrevious();) {
			ProductBean bean1 = (ProductBean) iterator.previous();

			// 最低価格以上の項目を出力する
			if (Integer.parseInt(PRICE_LIST.split(":")[0].trim()) <= bean1.getPrice()) {

				// 商品名がNULLの場合、「なし」を設定する
				if (null == bean1.getOfficial()) {
					bean1.setOfficial(PRODUCT_NAME_NONE);
				}

				// 表示
				displayData(bean1);
			}
		}
	}

	// bean1だけでは何のBeanかわからない。
	// 他にbeanを参照する処理が追加された場合など、変数名でなんの情報か判断できるようにしたい。
	// そのため、「bean1」を「productBean」に修正する。

	// 4. 「5.1.コメントするべきでは「ない」こと」
	private static void displayPriceData4(List<ProductBean> productsList) {

		productsList = sortOrder(productsList);

		// リスト逆順にイテレートする
		for (ListIterator<ProductBean> iterator = productsList
				.listIterator(productsList.size()); iterator.hasPrevious();) {
			ProductBean productBean = (ProductBean) iterator.previous();

			// 最低価格以上の項目を出力する
			if (Integer.parseInt(PRICE_LIST.split(":")[0].trim()) <= productBean.getPrice()) {

				// 商品名がNULLの場合、「なし」を設定する
				if (null == productBean.getOfficial()) {
					productBean.setOfficial(PRODUCT_NAME_NONE);
				}

				// 表示
				displayData(productBean);
			}
		}
	}

	// 「リスト逆順にイテレートする」とあるが、それはソースを見れば判断できる。
	// 何故逆順にする必要があるかをコメント文に記述する必要がある。
	// この場合、値段の高い順に出力するという理由がある。
	// そのため、コメントを「リストを値段の高い順にソートする」と変更する。

	// 5. 「8.1.説明変数」
	private static void displayPriceData5(List<ProductBean> productsList) {

		productsList = sortOrder(productsList);

		// リストを値段の高い順にソートする
		for (ListIterator<ProductBean> iterator = productsList
				.listIterator(productsList.size()); iterator.hasPrevious();) {
			ProductBean productBean = (ProductBean) iterator.previous();

			// 最低価格以上の項目を出力する
			if (Integer.parseInt(PRICE_LIST.split(":")[0].trim()) <= productBean.getPrice()) {

				// 商品名がNULLの場合、「なし」を設定する
				if (null == productBean.getOfficial()) {
					productBean.setOfficial(PRODUCT_NAME_NONE);
				}

				// 共通出力処理
				displayData(productBean);
			}
		}
	}

	// if文中に、文字列操作やIntegerにパースする記述が記述されている。
	// また、beanのアクセサ名が、仕様に対して連想しづらい名称となっている。
	// 読みにくい処理はすぐに把握しやすいように変数に分ける。

	// 6. 「5.3.読み手の立場になって考える」
	private static void displayPriceData6(List<ProductBean> productsList) {

		productsList = sortOrder(productsList);

		// リストを値段の高い順にソートする
		for (ListIterator<ProductBean> iterator = productsList
				.listIterator(productsList.size()); iterator.hasPrevious();) {
			ProductBean productBean = (ProductBean) iterator.previous();

			// 最低価格
			int basePrice = Integer.parseInt(PRICE_LIST.split(":")[0].trim());

			// 最低価格以上の項目を出力する
			if (basePrice <= productBean.getPrice()) {

				// 商品名がNULLの場合、「なし」を設定する
				String productName = productBean.getOfficial();
				if (null == productName) {
					productBean.setOfficial(PRODUCT_NAME_NONE);
				}

				// 共通出力処理
				displayData(productBean);
			}
		}
	}

	// 読み手は何故文字列をスプリットして取得しているかがわからない。
	// そのため最低価格の取得に関する仕様をを記述する。

	// 7. 「7.1.条件式の引数の並び順」
	private static void displayPriceData7(List<ProductBean> productsList) {

		productsList = sortOrder(productsList);

		// リストを値段の高い順にソートする
		for (ListIterator<ProductBean> iterator = productsList
				.listIterator(productsList.size()); iterator.hasPrevious();) {
			ProductBean productBean = (ProductBean) iterator.previous();

			// 最低価格(共通変数(:区切り)の最初の値を使用する)
			int basePrice = Integer.parseInt(PRICE_LIST.split(":")[0].trim());

			// 最低価格以上の項目を出力する
			if (basePrice <= productBean.getPrice()) {

				// 商品名がNULLの場合、「なし」を設定する
				String productName = productBean.getOfficial();
				if (null == productName) {
					productBean.setOfficial(PRODUCT_NAME_NONE);
				}

				// 共通出力処理
				displayData(productBean);
			}
		}
	}

	// 「もしあなたが18歳以上ならば～」と言うのが自然。
	// 「もし18年があなたの年齢以下ならば～」と言うのは不自然。
	// 通常、左側には可変する調査対象の値、右側にはあまり可変しない比較対象の値を記述する。
	// if文内の条件式の並びを変更する。

	// 9. 最後に
	private static void displayPriceData8(List<ProductBean> productsList) {

		productsList = sortOrder(productsList);

		// リストを値段の高い順にソートする
		for (ListIterator<ProductBean> iterator = productsList
				.listIterator(productsList.size()); iterator.hasPrevious();) {
			ProductBean productBean = (ProductBean) iterator.previous();

			// 最低価格(共通変数(:区切り)の最初の値を使用する)
			int basePrice = Integer.parseInt(PRICE_LIST.split(":")[0].trim());

			// 最低価格以上の項目を出力する
			if (productBean.getPrice() >= basePrice) {

				// 商品名がNULLの場合、「なし」を設定する
				String productName = productBean.getOfficial();
				if (productName == null) {
					productBean.setOfficial(PRODUCT_NAME_NONE);
				}

				// 共通出力処理
				displayData(productBean);
			}
		}
	}

	// 実は事前に行っているsortOrderメソッドは、値段を逆順にソートする処理を行っている。
	// 誰かが、このソースを読んだときには、リストを逆順にソートする処理は必要はない事がすぐに理解できる。
	// これはソースを読みやすくした結果、障害を発見したり対応したりする対応コストが低くなる。
	// 誰か、とは、自分以外のメンバかもしれないし、数ヵ月後の自分かもしれない。

	// あとがき
	//
	// これは、書籍「リーダブルコード――より良いコードを書くためのシンプルで実践的なテクニック」より
	// 抜粋して紹介しています。
	//
	// O'Reilly Japan - リーダブルコード http://www.oreilly.co.jp/books/9784873115658/
	//
	// 書籍にはリファクタリング時の注意点や、サンプルコードなど、
	// 他にもたくさんの情報があるので、興味がある人は読めばいいと思うよ。

	// 以下、動作用 ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ----
	// 記述する順番的に、本来この個所に書くべきではないのだが、説明上ソースの下部に記述する。

	/** 商品名がNULLの場合に表示する文字列 */
	private static final String PRODUCT_NAME_NONE = "なし";

	/**
	 * 表示価格範囲を設定している変数(:区切り)
	 * 最低価格:最高価格
	 */
	private static String PRICE_LIST = "2000:9000";

	/**
	 * 実行時に起動されるメインメソッド
	 * 割愛…
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		List<ProductBean> productsList = new ArrayList<ProductBean>();

		initProductBean(productsList);

		displayPriceData8(productsList);
//		getData(productsList);
	}

	/**
	 * 画面表示用メソッド
	 * …といいつつ、コンソールに出力するだけ。
	 *
	 * @param bean 商品Bean
	 */
	private static void displayData(ProductBean bean) {
		System.out.println(bean.getOfficial() + ":" + bean.getPrice());
	}


	/**
	 * 商品Beanリストをソートする機能
	 * …といいつつ、何もしてないよ。
	 *
	 * @param productsList 商品Beanリスト
	 * @return 商品Beanリスト
	 */
	private static List<ProductBean> sortOrder(List<ProductBean> productsList) {
		return productsList;
	}

	/**
	 * 商品Beanを作成します。
	 * 手打ち。
	 *
	 * @param productsList 商品Beanリスト
	 */
	private static void initProductBean(List<ProductBean> productsList) {
		ProductBean bean = new ProductBean();
		bean.setProductCode(1);
		bean.setOfficial("AAAA");
		bean.setPrice(1000);
		productsList.add(bean);

		bean = new ProductBean();
		bean.setProductCode(2);
		bean.setOfficial("BBBB");
		bean.setPrice(2000);
		productsList.add(bean);

		bean = new ProductBean();
		bean.setProductCode(3);
		bean.setOfficial(null);
		bean.setPrice(3000);
		productsList.add(bean);

		bean = new ProductBean();
		bean.setProductCode(4);
		bean.setOfficial("DDDD");
		bean.setPrice(4000);
		productsList.add(bean);
	}

}
