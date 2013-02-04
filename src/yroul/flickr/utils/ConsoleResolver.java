/*
 *
 * (c) Yoan Roullard <yoan.roullard@gmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package yroul.flickr.utils;

import yroul.flickr.view.FlickrExplorer;

public abstract class ConsoleResolver {
	
	public static void resolve(String args){
		if(args != null){
			if(args.equals("--help")){//display man page
				System.out.println("-----  FlickrExplorer  -----");
				System.out.println("\n\n");
				System.out.println("Author : Yoan Roullard (c) 2012");
				System.out.println("\n\n");
				System.out.println("Release under MIT License. See licence file for full license description");
				System.out.println("\n\n");
				System.out.println("FlickrExplorer [--help] [--version] [keywords]");
				System.out.println("Available options : \n");
				System.out.println("    --help  : Display this page");
				System.out.println("    --version  : Display software's version number");
				System.out.println("    --keywords  : Keywords you want to search");
				System.exit(0);
				System.out.println("\n\n");
				
			}
			if(args.equals("--version")){//display application version number
				System.out.println("FlickrExplorer version "+FlickrExplorer.VERSION);
				System.exit(0);
			}
		}
	}

}
