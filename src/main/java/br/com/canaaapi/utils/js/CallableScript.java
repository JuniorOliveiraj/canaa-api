package br.com.canaaapi.utils.js;

import java.util.Map;
import java.util.concurrent.Callable;


public class CallableScript implements Callable<Object> {

	private String js;
	private Map<String, Object> map;

	public CallableScript(String js, Map<String, Object> map) {
		this.js = js;
		this.map = map;
	}

	@Override
	public Object call() throws Exception {
		/*try {
			Context cx = Context.getCurrentContext();
			if (cx == null) {
				cx = Context.enter();
			}
			Scriptable scope = cx.initSafeStandardObjects();
			if (map != null && !map.isEmpty()) {
				for (String key : map.keySet()) {
					ScriptableObject.putProperty(scope, key, map.get(key));
				}
			}
			return cx.evaluateString(scope, js, "<js>", 1, null);
		} finally {
			Context.exit();
		}*/
		return "";
	}
}