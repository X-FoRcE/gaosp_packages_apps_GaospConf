package com.android.gaospconf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;


public class conf extends Activity {
    /** Called when the activity is first created. */
   
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Define variables
        String record = null;
                
        int sensors_sampling = 0;
        Boolean ssh = false;
        //Boolean inadyn = false;
        Boolean renice = false;
        Boolean prov = false;
        Boolean vnc = false;
        Boolean swap = false;
        Boolean bootani = false;
        Boolean gallery = false;
        
        // Define objects
        Button Default_Button = (Button) findViewById(R.id.defaults);
        Button Apply_Button = (Button) findViewById(R.id.apply);
        Button Service_Button = (Button) findViewById(R.id.service);
        Button Kitchen_Button = (Button) findViewById(R.id.kitchen);
        Button compcalibration_Button = (Button) findViewById(R.id.compcalibration);
        final CheckBox Toggle_SSH = (CheckBox) findViewById(R.id.ssh);
        final CheckBox Toggle_Renice = (CheckBox) findViewById(R.id.renice);
        final CheckBox Toggle_Prov = (CheckBox) findViewById(R.id.Prov);
        final CheckBox Toggle_VNC = (CheckBox) findViewById(R.id.vnc);
        final CheckBox Toggle_Swap = (CheckBox) findViewById(R.id.swap);    
        final CheckBox Toggle_Bootani = (CheckBox) findViewById(R.id.bootani);
        final CheckBox Toggle_Gallery = (CheckBox) findViewById(R.id.gallery);
        final Spinner presets = (Spinner) findViewById(R.id.presets);
        ArrayAdapter presetsArray = ArrayAdapter.createFromResource(this, R.array.Spresets, android.R.layout.simple_spinner_item);
        presetsArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        presets.setAdapter(presetsArray);
        presets.setSelection(6);
        final Spinner sensorsampling = (Spinner) findViewById(R.id.sensorsampling);
        final EditText memory1_edit = (EditText) findViewById(R.id.memory1);
        final EditText memory2_edit = (EditText) findViewById(R.id.memory2);
        final EditText memory3_edit = (EditText) findViewById(R.id.memory3);
        final EditText memory4_edit = (EditText) findViewById(R.id.memory4);
        final EditText memory5_edit = (EditText) findViewById(R.id.memory5);
        final EditText memory6_edit = (EditText) findViewById(R.id.memory6);
        final EditText lcddensity_edit = (EditText) findViewById(R.id.lcddensity);
        final EditText swappiness_edit = (EditText) findViewById(R.id.swappiness);
        final EditText sdcache_edit = (EditText) findViewById(R.id.sdcache);
        TextView Descswappiness = (TextView) findViewById(R.id.Textswappiness);
        TextView Descsdcache = (TextView) findViewById(R.id.Textsdcache);
        TextView Descswap = (TextView) findViewById(R.id.Textswap);
		TextView Descsensors = (TextView) findViewById(R.id.Textsensors);
		TextView Descssh = (TextView) findViewById(R.id.Textssh);
		TextView Descrenice = (TextView) findViewById(R.id.Textrenice);
		TextView Descprovisionned = (TextView) findViewById(R.id.Textprovisionned);
		TextView Descvnc = (TextView) findViewById(R.id.Textvnc);
		TextView Descbootani = (TextView) findViewById(R.id.Textbootani);
		TextView Descgallery = (TextView) findViewById(R.id.Textgallery);
		TextView Descservice = (TextView) findViewById(R.id.Textservice);
		TextView Desckitchen = (TextView) findViewById(R.id.Textkitchen);
		TextView Descmemorythresholds = (TextView) findViewById(R.id.Textmemorythresholds);		
		TextView Descforegroundapp = (TextView) findViewById(R.id.Textforegroundapp);
		TextView Descvisibleapp = (TextView) findViewById(R.id.Textvisibleapp);
		TextView Descsecondaryapp = (TextView) findViewById(R.id.Textsecondaryapp);
		TextView Deschiddenapp = (TextView) findViewById(R.id.Texthiddenapp);
		TextView Desccontentapp = (TextView) findViewById(R.id.Textcontentapp);
		TextView Descemptyapp = (TextView) findViewById(R.id.Textemptyapp);
		TextView Desclcddensity = (TextView) findViewById(R.id.Textlcddensity);
		TextView Desccompcalibration = (TextView) findViewById(R.id.Textcompcalibration);
		TextView Descpresets = (TextView) findViewById(R.id.Textpresets);
        final AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
	
        // Open config file
        FileReader FR = null;
		try {
			FR = new FileReader("/system/etc/gaosp.conf");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} 
		BufferedReader BR = new BufferedReader(FR, 8192);	
		// Read config file
		try {
			while ((record = BR.readLine()) != null) {
			    if (record.equals("sshd=no")) {
			    	ssh = false;
			    }
			    if (record.equals("sshd=yes")) {
			    	ssh = true;
			    }
			    /*
			     * if (record.equals("inadyn=no")) {
			     * 	inadyn = false;
			     * }
			     * if (record.equals("inadyn=yes")) {
			     * 	inadyn = true;			    	
			     * }
			    */ 
			    if (record.equals("renice=no")) {
			    	renice = false;
			    }
			    if (record.equals("renice=yes")) {
			    	renice = true;
			    }
			    if (record.equals("provisionned=no")) {
			    	prov = false;
			    }
			    if (record.equals("provisionned=yes")) {
			    	prov = true;
			    }
			    if (record.equals("vnc=no")) {
			    	vnc = false;
			    }
			    if (record.equals("vnc=yes")) {
			    	vnc = true;
			    }
			    if (record.equals("swap=no")) {
			    	swap = false;
			    }
			    if (record.equals("swap=yes")) {
			    	swap = true;
			    }
			    if (record.startsWith("swappiness=")) {
			    	swappiness_edit.setText(record.substring(11));
			    }
			    if (record.equals("bootani=no")) {
			    	bootani = false;
			    }
			    if (record.equals("bootani=yes")) {
			    	bootani = true;
			    }
			    if (record.equals("gallery=no")) {
			    	gallery = false;
			    }
			    if (record.equals("gallery=yes")) {
			    	gallery = true;
			    }
			    if (record.startsWith("sensors_sampling=")) {
			    	sensors_sampling = Integer.parseInt(record.substring(17));
			    }
			    if (record.startsWith("mem1=")) {
			    	memory1_edit.setText(Integer.toString(Integer.parseInt(record.substring(5))*4/1024));
			    }
			    if (record.startsWith("mem2=")) {
			    	memory2_edit.setText(Integer.toString(Integer.parseInt(record.substring(5))*4/1024));
			    }
			    if (record.startsWith("mem3=")) {
			    	memory3_edit.setText(Integer.toString(Integer.parseInt(record.substring(5))*4/1024));
			    }
			    if (record.startsWith("mem4=")) {
			    	memory4_edit.setText(Integer.toString(Integer.parseInt(record.substring(5))*4/1024));
			    }
			    if (record.startsWith("mem5=")) {
			    	memory5_edit.setText(Integer.toString(Integer.parseInt(record.substring(5))*4/1024));
			    }
			    if (record.startsWith("mem6=")) {
			    	memory6_edit.setText(Integer.toString(Integer.parseInt(record.substring(5))*4/1024));
			    }
			    if (record.startsWith("sdcache=")) {
			    	sdcache_edit.setText(record.substring(8));
			    }
			}
			BR.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		// Open build.prop file (LCD Density)
		FR = null;
		try {
			FR = new FileReader("/system/build.prop");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} 
		BR = new BufferedReader(FR, 8192);	
		// Read build.prop file (LCD Density)
		try {
		    while ((record = BR.readLine()) != null) {
		    	if (record.startsWith("ro.sf.lcd_density=")) {
		    		lcddensity_edit.setText(record.substring(18));
		    	}
			}
    		BR.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Set read data
		if (ssh == true) {
			Toggle_SSH.setChecked(true);
		}
		if (renice == true) {
			Toggle_Renice.setChecked(true);
		}
		if (prov == true) {
			Toggle_Prov.setChecked(true);
		}
		if (vnc == true) {
			Toggle_VNC.setChecked(true);
		}
		if (swap == true) {
			Toggle_Swap.setChecked(true); }
		else {
			swappiness_edit.setEnabled(false);
		}	
		if (bootani == true) {
			Toggle_Bootani.setChecked(true);
		}
		if (gallery == true) {
			Toggle_Gallery.setChecked(true);
		}
		switch (sensors_sampling) {
			case 0 : sensorsampling.setSelection(0); break;
			case 1 : sensorsampling.setSelection(1); break;
			case 2 : sensorsampling.setSelection(2); break;
		}
		
		// Show startup info
    	Toast.makeText(getBaseContext(), R.string.startup, Toast.LENGTH_LONG).show();

    	// TextView Listener (Descriptions)
		Descswap.setOnClickListener(new View.OnClickListener() {
    	public void onClick(View v){ 	
    		alertbox.setTitle(R.string.TVswap);
    		alertbox.setMessage(R.string.swap);
            alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                }
            });
    		alertbox.show();
    		}
        });
		Descswappiness.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View v){ 	
	    		alertbox.setTitle(R.string.TVswappiness);
	    		alertbox.setMessage(R.string.swappiness);
	            alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface arg0, int arg1) {
	                }
	            });
	    		alertbox.show();
	    		}
	        });
		Descpresets.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View v){ 	
	    		alertbox.setTitle(R.string.TVpresets);
	    		alertbox.setMessage(R.string.presets);
	            alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface arg0, int arg1) {
	                }
	            });
	    		alertbox.show();
	    		}
	        });
		Descsensors.setOnClickListener(new View.OnClickListener() {
    	public void onClick(View v){ 						
    		alertbox.setTitle(R.string.TVsensors);
    		alertbox.setMessage(R.string.sensors);
            alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                }
            });
    		alertbox.show();
    		}
        });
		Descssh.setOnClickListener(new View.OnClickListener() {
    	public void onClick(View v){ 	
    		alertbox.setTitle(R.string.TVssh);
    		alertbox.setMessage(R.string.ssh);
            alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                }
            });
    		alertbox.show();
    		}
        });
		Descrenice.setOnClickListener(new View.OnClickListener() {
    	public void onClick(View v){ 	
    		alertbox.setTitle(R.string.TVrenice);
    		alertbox.setMessage(R.string.renice);
            alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                }
            });
    		alertbox.show();
    		}
        });
		Descprovisionned.setOnClickListener(new View.OnClickListener() {
    	public void onClick(View v){ 	
    		alertbox.setTitle(R.string.TVprovisionned);
    		alertbox.setMessage(R.string.provisionned);
            alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                }
            });
    		alertbox.show();
    		}
        });
		Descvnc.setOnClickListener(new View.OnClickListener() {
    	public void onClick(View v){ 	
    		alertbox.setTitle(R.string.TVvnc);
    		alertbox.setMessage(R.string.vnc);
            alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                }
            });
    		alertbox.show();
    		}
        });
		Descbootani.setOnClickListener(new View.OnClickListener() {
    	public void onClick(View v){ 	
    		alertbox.setTitle(R.string.TVbootani);
    		alertbox.setMessage(R.string.bootani);
            alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                }
            });
    		alertbox.show();
    		}
        });
		Desclcddensity.setOnClickListener(new View.OnClickListener() {
	    public void onClick(View v){ 	
	    	alertbox.setTitle(R.string.TVlcddensity);
	    	alertbox.setMessage(R.string.lcddensity);
	        alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
	        	public void onClick(DialogInterface arg0, int arg1) {
	            }
	        });
	    	alertbox.show();
	    	}
	    });
		Descgallery.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v){ 	
		   	alertbox.setTitle(R.string.TVgallery);
		   	alertbox.setMessage(R.string.gallery);
		    alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
		       	public void onClick(DialogInterface arg0, int arg1) {
		        }
		    });
		   	alertbox.show();
		    }
		});
		Descservice.setOnClickListener(new View.OnClickListener() {
    	public void onClick(View v){ 	
    		alertbox.setTitle(R.string.TVservicemode);
    		alertbox.setMessage(R.string.servicemode);
            alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                }
            });
    		alertbox.show();
    		}
        });   
		Desckitchen.setOnClickListener(new View.OnClickListener() {
    	public void onClick(View v){ 	
    		alertbox.setTitle(R.string.TVkitchen);
    		alertbox.setMessage(R.string.kitchen);
            alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                }
            });
    		alertbox.show();
    		}
        });
		Descsdcache.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View v){ 	
	    	alertbox.setTitle(R.string.TVsdcache);
	    	alertbox.setMessage(R.string.sdcache);
	        alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface arg0, int arg1) {
	            }
	        });
	    	alertbox.show();
	    	}
	    });
		Descmemorythresholds.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View v){
	    		alertbox.setTitle(R.string.TVmemorythresholds);
	    		alertbox.setMessage(R.string.memorythresholds);
	            alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface arg0, int arg1) {
	                }
	            });
	    		alertbox.show();
	    		}
	    });
		Descforegroundapp.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View v){ 	
	    		alertbox.setTitle(R.string.TVforegroundapp);
	    		alertbox.setMessage(R.string.foregroundapp);
	            alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface arg0, int arg1) {
	                }
	            });
	    		alertbox.show();
	    		}
	    });
		Descvisibleapp.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View v){ 	
	    		alertbox.setTitle(R.string.TVvisibleapp);
	    		alertbox.setMessage(R.string.visibleapp);
	            alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface arg0, int arg1) {
	                }
	            });
	    		alertbox.show();
	    		}
	    });
		Descsecondaryapp.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View v){ 	
	    		alertbox.setTitle(R.string.TVsecondaryapp);
	    		alertbox.setMessage(R.string.secondaryapp);
	            alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface arg0, int arg1) {
	                }
	            });
	    		alertbox.show();
	    		}
	    });
		Deschiddenapp.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View v){ 	
	    		alertbox.setTitle(R.string.TVhiddenapp);
	    		alertbox.setMessage(R.string.hiddenapp);
	            alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface arg0, int arg1) {
	                }
	            });
	    		alertbox.show();
	    		}
	    });
		Desccontentapp.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View v){ 	
	    		alertbox.setTitle(R.string.TVcontentapp);
	    		alertbox.setMessage(R.string.contentapp);
	            alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface arg0, int arg1) {
	                }
	            });
	    		alertbox.show();
	    		}
	    });
		Descemptyapp.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View v){ 	
	    		alertbox.setTitle(R.string.TVemptyapp);
	    		alertbox.setMessage(R.string.emptyapp);
	            alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface arg0, int arg1) {
	                }
	            });
	    		alertbox.show();
	    		}
	    });
		Desccompcalibration.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View v){ 	
	    		alertbox.setTitle(R.string.TVcompcalibration);
	    		alertbox.setMessage(R.string.compcalibration);
	            alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface arg0, int arg1) {
	                }
	            });
	    		alertbox.show();
	    		}
	    });
		
		// Button Listener
		presets.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				switch(presets.getSelectedItemPosition()) {
						case 0:
							memory1_edit.setText("6");
							memory2_edit.setText("8");
							memory3_edit.setText("16");
							memory4_edit.setText("17");
							memory5_edit.setText("18");
							memory6_edit.setText("19"); break;
						case 1:
							memory1_edit.setText("6");
							memory2_edit.setText("8");
							memory3_edit.setText("16");
							memory4_edit.setText("20");
							memory5_edit.setText("22");
							memory6_edit.setText("24"); break;
						case 2:
							memory1_edit.setText("6");
							memory2_edit.setText("8");
							memory3_edit.setText("16");
							memory4_edit.setText("25");
							memory5_edit.setText("30");
							memory6_edit.setText("35"); break;	
						case 3:
							memory1_edit.setText("6");
							memory2_edit.setText("8");
							memory3_edit.setText("16");
							memory4_edit.setText("25");
							memory5_edit.setText("30");
							memory6_edit.setText("35"); break;
						case 4:
							memory1_edit.setText("6");
							memory2_edit.setText("8");
							memory3_edit.setText("16");
							memory4_edit.setText("22");
							memory5_edit.setText("24");
							memory6_edit.setText("30"); break;
						case 5:
							memory1_edit.setText("6");
							memory2_edit.setText("8");
							memory3_edit.setText("16");
							memory4_edit.setText("36");
							memory5_edit.setText("40");
							memory6_edit.setText("40"); break;
				}				
			}
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		Service_Button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
	            	String encodedHash = Uri.encode("#");
	                Intent intent = new Intent("android.intent.action.DIAL",  Uri.parse("tel:*"+encodedHash+"*"+encodedHash+"197328640"+encodedHash+"*"+encodedHash+"*"));
	                startActivity(intent);
	                // Thanks Samsung to code with your foot ;)
	                // Intent intent = new Intent();
	                // intent.setClassName("com.android.serviceModeApp","com.android.serviceModeApp.serviceModeApp");
	                // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
        });
		Kitchen_Button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse("http://kitchen.yaam.mobi/"));
				startActivity(intent);
            }
        });
		compcalibration_Button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// delete old calibration file
				String[] delcalibration = { "/system/xbin/su -c 'rm /data/misc/akmd_set.txt'", "echo deleted akmd_set.txt"};
                shell.doExec(delcalibration, true);
			}
		});
		
		// Default Button
		Default_Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
				Toggle_SSH.setChecked(false);
				Toggle_Renice.setChecked(true);
				Toggle_Prov.setChecked(true);
				Toggle_VNC.setChecked(false);
				sensorsampling.setSelection(0);
				swappiness_edit.setText("15");
				Toggle_Swap.setChecked(false);
				Toggle_Bootani.setChecked(true);
				Toggle_Gallery.setChecked(false);
				lcddensity_edit.setText("160");
				sdcache_edit.setText("128");
				presets.setSelection(4);  	
				Toast.makeText(getBaseContext(), R.string.defaults, Toast.LENGTH_LONG).show();
            }
        });
		
		// Apply Button
		Apply_Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	PrintWriter out = null;
            		
	            	// Remount in read/write
            		String[] rw = { "/system/xbin/su -c /system/xbin/remountrw", "echo remount rw done"};
            		shell.doExec(rw, true);
            		
	            	try {
						out  = new PrintWriter(new FileWriter("/system/etc/gaosp.conf"));
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					out.println("################################");
					out.println("##### Gaosp config file ########");
					out.println("################################");
					out.println(" ");
									
					// Copy SSH setting to conf file
					out.println("# SSH server");
					if (Toggle_SSH.isChecked()) {
						out.println("sshd=yes");
					}
					else {
						out.println("sshd=no");
					}
					out.println(" ");
					
					// Copy inadyn setting to conf file
					out.println("# Inadyn");
					out.println("inadyn=no");
					out.println(" ");
					
					// Copy Renice setting to conf file
					out.println("# Renice");
					if (Toggle_Renice.isChecked()) {
						out.println("renice=yes");
					}
					else {
						out.println("renice=no");
					}
					out.println(" ");
					
					// Copy Provisioned setting to conf file
					out.println("# Device provisionned");
					if (Toggle_Renice.isChecked()) {
						out.println("provisionned=yes");
					}
					else {
						out.println("provisionned=no");
					}
					out.println(" ");
					
					// Copy VNC Server setting to conf file
					out.println("# VNC Server");
					if (Toggle_VNC.isChecked()) {
						out.println("vnc=yes");
					}
					else {
						out.println("vnc=no");
					}
					out.println(" ");
					
					// Copy Swap setting to conf file
					out.println("# Swap");
					if (Toggle_Swap.isChecked()) {
						out.println("swap=yes");
					}
					else {															
						out.println("swap=no");					
					}
					out.println(" ");
					
					// Copy Swappiness setting to conf file
					out.println("# Swappiness");
					out.println("swappiness="+swappiness_edit.getText().toString());
					out.println(" ");
					
					// Copy Bootanimation setting to conf file
					out.println("# Bootanimation");
					if (Toggle_Bootani.isChecked()) {
						out.println("bootani=yes");
						if (new File("/system/media/bootanimation.zip").length() > 3000000 ) {
				    		String[] bootchange = { "/system/xbin/su -c 'mv /system/media/bootanimation.zip /system/media/bootanimation_temp.zip'",
				    								"/system/xbin/su -c 'mv /system/media/bootanimation_old.zip /system/media/bootanimation.zip'",
				    								"/system/xbin/su -c 'mv /system/media/bootanimation_temp.zip /system/media/bootanimation_old.zip'",
				    								"echo Bootanimation changed"};
				    		shell.doExec(bootchange, true);
							}
					}
					else {
						out.println("bootani=no");
						if (new File("/system/media/bootanimation.zip").length() < 3000000 ) {
				    		String[] bootchange = { "/system/xbin/su -c 'mv /system/media/bootanimation.zip /system/media/bootanimation_temp.zip'",
				    								"/system/xbin/su -c 'mv /system/media/bootanimation_old.zip /system/media/bootanimation.zip'",
				    								"/system/xbin/su -c 'mv /system/media/bootanimation_temp.zip /system/media/bootanimation_old.zip'",
				    								"echo Bootanimation changed"};
				    		shell.doExec(bootchange, true);
							}
					}
					out.println(" ");
					
					// Copy Gallery setting to conf file
					out.println("# Gallery");
					if (Toggle_Gallery.isChecked()) {
						out.println("gallery=yes");
						if (new File("/data/app_s/Gallery.apk").exists() == false) {
				    		String[] gallerychange1 = { "/system/xbin/su -c 'mv /data/app_s/Gallery3D.apk /data/app_s/Gallery3D.no'",
				    								   	"/system/xbin/su -c 'mv /data/app_s/Gallery.no /data/app_s/Gallery.apk'",
				    								   	"echo Gallery changed"};
				    		shell.doExec(gallerychange1, true);
							}
					}
					else {
						out.println("gallery=no");
						if (new File("/data/app_s/Gallery3D.apk").exists() == false) {
				    		String[] gallerychange2 = { "/system/xbin/su -c 'mv /data/app_s/Gallery3D.no /data/app_s/Gallery3D.apk'",
				    									"/system/xbin/su -c 'mv /data/app_s/Gallery.apk /data/app_s/Gallery.no'",
				    									"echo Gallery changed"};
				    		shell.doExec(gallerychange2, true);
							}		
					}
					out.println(" ");
																		
					// Copy Sensors setting to conf file
					out.println("# Sensors sampling rate");
					out.println("# Set to 0 to eco mode, 1 to mixte mode, 2 to Performance mode");
					out.println("sensors_sampling=" + sensorsampling.getSelectedItemPosition());
					out.println(" ");
					
					// Copy minfree settings to conf file
					out.println("# Minfree settings");
					out.println("mem1=" + Integer.parseInt(memory1_edit.getText().toString())*1024/4);
					out.println("mem2=" + Integer.parseInt(memory2_edit.getText().toString())*1024/4);
					out.println("mem3=" + Integer.parseInt(memory3_edit.getText().toString())*1024/4);
					out.println("mem4=" + Integer.parseInt(memory4_edit.getText().toString())*1024/4);
					out.println("mem5=" + Integer.parseInt(memory5_edit.getText().toString())*1024/4);
					out.println("mem6=" + Integer.parseInt(memory6_edit.getText().toString())*1024/4);
					out.println(" ");
										
					// Copy SD Cache setting to conf file
					out.println("# SD Read Cache");
					out.println("sdcache=" + sdcache_edit.getText());
					out.println(" ");
					
					// Set LCD Density
					String[] lcdchange = { "density=`grep ro.sf.lcd_density= /system/build.prop | awk -F = '{print $2'}`",
										   "/system/xbin/su -c 'sed -i 's/ro.sf.lcd_density=$density/ro.sf.lcd_density="+lcddensity_edit.getText().toString()+"/' /system/build.prop'",
										   "echo LCD Density changed"};
					shell.doExec(lcdchange, true);
					
					// Executing rc with scrolling
					new Task().execute();
					
					// Close file
					out.flush();
					out.close();
					
					// Remount in read only
					String[] ro = { "/system/xbin/su -c /system/xbin/remountro", "echo remount ro done" };
            		shell.doExec(ro, true);
            }
        });
		// Check for dependencies
		Toggle_Swap.setOnCheckedChangeListener(new OnCheckedChangeListener()
		{
			public void onCheckedChanged(CompoundButton buttonView,	boolean isChecked) {
				if (Toggle_Swap.isChecked()) {
					swappiness_edit.setEnabled(true);
					if (new File("/dev/block/mmcblk1p2").exists() == false) {
						Toast.makeText(getBaseContext(), R.string.swapwarning, Toast.LENGTH_LONG).show();
					}
				} else {
					swappiness_edit.setEnabled(false);
				}
			}
		});
	}
    
	// Create Menu
    public boolean onCreateOptionsMenu(Menu menu) 
    {
    	super.onCreateOptionsMenu(menu);
    	MenuItem item = menu.add("About");
    	item = menu.add("Exit");
		return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item)
      {
         if (item.hasSubMenu() == false)
         {
           	if (item.getTitle() == "About") {
           		// Print the about
                AlertDialog.Builder dialogBuilder = new 
                AlertDialog.Builder(this);
                dialogBuilder.setTitle("About");
                dialogBuilder.setMessage("GAOSP Configuration 2 by drakaz and Obihoernchen");
                dialogBuilder.setCancelable(true);
            	dialogBuilder.create().show();
           	}
        	if (item.getTitle() == "Exit") {
        		this.finish();
        	}
         }
         // Consume the selection event.
         return true;
       }    
    
    final class Task extends UserTask<String, Void, Void> {
    	// Create ProgressDialog
    	ProgressDialog myProgressDialog;
    	protected void onPreExecute() {
    			 myProgressDialog = ProgressDialog.show(conf.this,"", getString(R.string.wait), true, true);
    	}
    	// Execute rc
    	@Override
    	protected Void doInBackground(String... params) {
    		String[] rc = { "/system/xbin/su -c /system/bin/rc", "echo rc executed" };
    		shell.doExec(rc, true);
			return null;
    	}
    	// Success message
    	protected void onPostExecute(Void unused) {
    			
    			if (myProgressDialog.isShowing()) {
    				myProgressDialog.dismiss();
	            	Toast.makeText(getBaseContext(), R.string.applied, Toast.LENGTH_LONG).show();
    			}
    	}
    }
}
