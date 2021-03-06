System properties
=============
A list of system properties that users can configure to alter PDFsam behavior.

**org.pdfsam.modules.directory**  
*values:* existing directory  
*default:* empty  
*description:* lets the user specify where the PDFsam modules are located in the filesystem. By default PDFsam searches for modules jars in its installation directory under the ```modules``` subdirectory.  


**org.pdfsam.disable.ui.restore**  
*values:* true|false  
*default:* false  
*description:* if set to true PDFsam will not restore the stage position, dimensions and mode, it will start with default values instead (maximized).  


**org.pdfsam.default.locale**  
*values:* a locale  
*default:* default locale  
*description:* if no locale is set in the application preferences, tells PDFsam to use this locale.   


**org.pdfsam.default.checkforupdate**  
*values:* true|false  
*default:* true   
*description:* if not already set in the application preferences, tells PDFsam if it should check for updates at startup. 


**org.pdfsam.default.news**  
*values:* NEVER|ONCE_A_WEEK|ONCE_A_DAY|ALWAYS   
*default:* ONCE_A_WEEK   
*description:* if not already set in the application preferences, tells PDFsam how often it should open the news panel. 


Runtime arguments
=============
**-clean**  
*description:* clears user preferences starting PDFsam with default values for them. 
