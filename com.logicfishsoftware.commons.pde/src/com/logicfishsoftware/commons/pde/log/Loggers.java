package com.logicfishsoftware.commons.pde.log;

import org.eclipse.core.runtime.Platform;

public class Loggers {
	public interface Log {
		void warn(Exception e);
		void warn(String msg);
		void error(Exception e);
		void info(String msg);
	}
	public static Log getLogger(final String id,final Class<?> name) {
		if(!Platform.isRunning()) {
			return new Log(){

				@Override
				public void warn(Exception e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void warn(String msg) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void error(Exception e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void info(String msg) {
					// TODO Auto-generated method stub
					
				}
			};
		}
		return new Log(){

			@Override
			public void warn(Exception e) {
				PlatformLog.warn(id, e);
			}

			@Override
			public void warn(String msg) {
				PlatformLog.warn(id, msg);
			}

			@Override
			public void error(Exception e) {
				PlatformLog.error(id, e);
			}

			@Override
			public void info(String msg) {
				PlatformLog.log(id, msg);
			}
		};			
	}
}
