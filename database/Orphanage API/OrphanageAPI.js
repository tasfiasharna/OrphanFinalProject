
                                ///////////////////////////////////////////////////////////////////////////////////////////////////
                                //     ____             __                                    ___    ____  ____   ____   ___     //
                                //    / __ \_________  / /_  ____ _____  ____ _____ ____     /   |  / __ \/  _/  / __ \ <  /     //
                                //   / / / / ___/ __ \/ __ \/ __ `/ __ \/ __ `/ __ `/ _ \   / /| | / /_/ // /   / / / / / /      //
                                //  / /_/ / /  / /_/ / / / / /_/ / / / / /_/ / /_/ /  __/  / ___ |/ ____// /   / /_/ / / /       //
                                //  \____/_/  / .___/_/ /_/\__,_/_/ /_/\__,_/\__, /\___/  /_/  |_/_/   /___/   \____(_)_/        //
                                //           /_/                            /____/                                               //
                                ///////////////////////////////////////////////////////////////////////////////////////////////////







                                //   /$$$$$$           /$$   /$$     /$$           /$$ /$$           /$$                                
                                //  |_  $$_/          |__/  | $$    |__/          | $$|__/          |__/                                
                                //    | $$   /$$$$$$$  /$$ /$$$$$$   /$$  /$$$$$$ | $$ /$$ /$$$$$$$$ /$$ /$$$$$$$   /$$$$$$             
                                //    | $$  | $$__  $$| $$|_  $$_/  | $$ |____  $$| $$| $$|____ /$$/| $$| $$__  $$ /$$__  $$            
                                //    | $$  | $$  \ $$| $$  | $$    | $$  /$$$$$$$| $$| $$   /$$$$/ | $$| $$  \ $$| $$  \ $$            
                                //    | $$  | $$  | $$| $$  | $$ /$$| $$ /$$__  $$| $$| $$  /$$__/  | $$| $$  | $$| $$  | $$            
                                //   /$$$$$$| $$  | $$| $$  |  $$$$/| $$|  $$$$$$$| $$| $$ /$$$$$$$$| $$| $$  | $$|  $$$$$$$ /$$ /$$ /$$
                                //  |______/|__/  |__/|__/   \___/  |__/ \_______/|__/|__/|________/|__/|__/  |__/ \____  $$|__/|__/|__/
                                //                                                                                 /$$  \ $$            
                                //                                                                                |  $$$$$$/            
                                //                                                                                 \______/             




                //Initiallising node modules
                var express = require("express");
                var bodyParser = require("body-parser");
                var sql = require("mssql");
                var app = express();

                // Setting Base directory
                app.use(bodyParser.json({ limit: '50mb' }));
                app.use(bodyParser.urlencoded({ limit: '50mb', extended: true }));
                //CORS Middleware
                app.use(function (req, res, next) {
                    //Enabling CORS 
                    res.header("Access-Control-Allow-Origin", "*");
                    res.header("Access-Control-Allow-Methods", "GET,HEAD,OPTIONS,POST,PUT");
                    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, contentType,Content-Type, Accept, Authorization");
                    next();
                });



                                                    //                                            _               
                                                    //    ___  ___ _ ____   _____ _ __   ___  ___| |_ _   _ _ __  
                                                    //   / __|/ _ \ '__\ \ / / _ \ '__| / __|/ _ \ __| | | | '_ \ 
                                                    //   \__ \  __/ |   \ V /  __/ |    \__ \  __/ |_| |_| | |_) |
                                                    //   |___/\___|_|    \_/ \___|_|    |___/\___|\__|\__,_| .__/ 
                                                    //                                                     |_|    



                //Setting up server
                var server = app.listen(process.env.PORT || 8080, function () {
                    var port = server.address().port;
                    console.log("App is now running on port", port);
                });


                                                    //                                   _   _                   _        _             
                                                    //                                  | | (_)                 | |      (_)            
                                                    //    ___ ___  _ __  _ __   ___  ___| |_ _  ___  _ __    ___| |_ _ __ _ _ __   __ _ 
                                                    //   / __/ _ \| '_ \| '_ \ / _ \/ __| __| |/ _ \| '_ \  / __| __| '__| | '_ \ / _` |
                                                    //  | (_| (_) | | | | | | |  __/ (__| |_| | (_) | | | | \__ \ |_| |  | | | | | (_| |
                                                    //   \___\___/|_| |_|_| |_|\___|\___|\__|_|\___/|_| |_| |___/\__|_|  |_|_| |_|\__, |
                                                    //                                                                             __/ |
                                                    //                                                                            |___/ 


                //Initiallising connection string
                var dbConfig = {
                    user: 'sa',
                    password: 'sa@123',
                    server: 'localhost',
                    database: 'OrphanageManagementSystemDb'
                };

                                                    //     ___                            _      ___       __                     _       
                                                    //    / __\___  _ __  _ __   ___  ___| |_   ( _ )     /__\_  _____  ___ _   _| |_ ___ 
                                                    //   / /  / _ \| '_ \| '_ \ / _ \/ __| __|  / _ \/\  /_\ \ \/ / _ \/ __| | | | __/ _ \
                                                    //  / /__| (_) | | | | | | |  __/ (__| |_  | (_>  < //__  >  <  __/ (__| |_| | ||  __/
                                                    //  \____/\___/|_| |_|_| |_|\___|\___|\__|  \___/\/ \__/ /_/\_\___|\___|\__,_|\__\___|
                                                    //                                                                                    




                        //   .----------------.  .----------------.  .----------------.   .----------------.  .----------------.  .----------------.  .----------------. 
                        //  | .--------------. || .--------------. || .--------------. | | .--------------. || .--------------. || .--------------. || .--------------. |
                        //  | |      __      | || |   ______     | || |     _____    | | | |   _____      | || |     _____    | || |    _______   | || |  _________   | |
                        //  | |     /  \     | || |  |_   __ \   | || |    |_   _|   | | | |  |_   _|     | || |    |_   _|   | || |   /  ___  |  | || | |  _   _  |  | |
                        //  | |    / /\ \    | || |    | |__) |  | || |      | |     | | | |    | |       | || |      | |     | || |  |  (__ \_|  | || | |_/ | | \_|  | |
                        //  | |   / ____ \   | || |    |  ___/   | || |      | |     | | | |    | |   _   | || |      | |     | || |   '.___`-.   | || |     | |      | |
                        //  | | _/ /    \ \_ | || |   _| |_      | || |     _| |_    | | | |   _| |__/ |  | || |     _| |_    | || |  |`\____) |  | || |    _| |_     | |
                        //  | ||____|  |____|| || |  |_____|     | || |    |_____|   | | | |  |________|  | || |    |_____|   | || |  |_______.'  | || |   |_____|    | |
                        //  | |              | || |              | || |              | | | |              | || |              | || |              | || |              | |
                        //  | '--------------' || '--------------' || '--------------' | | '--------------' || '--------------' || '--------------' || '--------------' |
                        //   '----------------'  '----------------'  '----------------'   '----------------'  '----------------'  '----------------'  '----------------' 



                                    //        __       ________   ___      ___   __    _____  ___       ___        ______    _______   __    _____  ___   
                                    //       /""\     |"      "\ |"  \    /"  | |" \  (\"   \|"  \     |"  |      /    " \  /" _   "| |" \  (\"   \|"  \  
                                    //      /    \    (.  ___  :) \   \  //   | ||  | |.\\   \    |    ||  |     // ____  \(: ( \___) ||  | |.\\   \    | 
                                    //     /' /\  \   |: \   ) || /\\  \/.    | |:  | |: \.   \\  |    |:  |    /  /    ) :)\/ \      |:  | |: \.   \\  | 
                                    //    //  __'  \  (| (___\ |||: \.        | |.  | |.  \    \. |     \  |___(: (____/ // //  \ ___ |.  | |.  \    \. | 
                                    //   /   /  \\  \ |:       :)|.  \    /:  | /\  |\|    \    \ |    ( \_|:  \\        / (:   _(  _|/\  |\|    \    \ | 
                                    //  (___/    \___)(________/ |___|\__/|___|(__\_|_)\___|\____\)     \_______)\"_____/   \_______)(__\_|_)\___|\____\) 
                                    //                                                                                                                    


                //GET API
                app.get("/api/admin/user/android/:email/:pass", function (req, res) {
                    var query = "select Id from [AdminLogIn] where Email = '" + req.params.email + "' and aPassword = '" + req.params.pass +"'";
                    sql.connect(dbConfig, function (err) {
                        if (err) {
                            console.log("Error while connecting database :- " + err);
                            res.send(err);
                        }
                        else {
                            //Connection success notification
                            console.log("Successfully connected to the " + dbConfig.database);
                            //Connection success notification


                            // create Request object
                            var request = new sql.Request();
                            // query to the database
                            request.query(query, function (err, data) {
                                var checker = data.recordset[0];
                                if (err) {
                                    console.log("Error while querying database :- " + err);
                                    res.send(err);
                                }
                                else {
                                    if (checker !== undefined) {
                                        res.send("logged in successfully");
                                    } else {
                                        res.send("user not found");
                                    }
                                    console.log(checker);
                                    //console.log("response.body: " + res.body);
                                    //console.log(res.recordset[0].UserName);
                                }
                            });
                        }
                    });
                });

                //POST API
                app.post("/api/admin/user", function (req, res) {
                    var query = "INSERT INTO [AdminLogIn] (UserName, aPassword, Email, Skills, Education) VALUES ('" + req.body.UserName + "','" + req.body.aPassword + "','" + req.body.Email + "','" + req.body.Skills + "','" +req.body.Education +"')";
                    sql.connect(dbConfig, function (err) {
                        if (err) {
                            console.log("Error while connecting database :- " + err);
                            res.send(err);
                        }
                        else {
                            //Connection success notification
                            console.log("Successfully connected to the " + dbConfig.database);
                            //Connection success notification


                            // create Request object
                            var request = new sql.Request();
                            // query to the database
                            request.query(query, function (err, data) {
                                if (err) {
                                    console.log("Error while querying database :- " + err);
                                    res.send(err);
                                }
                                else {
                                    res.send(data);
                                    //console.log("response.body: " + res.body);
                                    //console.log(res.recordset[0].UserName);
                                }
                            });
                        }
                    });
                });

                //PUT API
                app.put("/api/admin/user/:id", function (req, res) {
                    var query = "UPDATE [AdminLogIn] SET UserName= '" + req.body.UserName + "' , Email=  '" + req.body.Email + "'  WHERE Id= " + req.params.id;
                    sql.connect(dbConfig, function (err) {
                        if (err) {
                            console.log("Error while connecting database :- " + err);
                            res.send(err);
                        }
                        else {
                            //Connection success notification
                            console.log("Successfully connected to the " + dbConfig.database);
                            //Connection success notification


                            // create Request object
                            var request = new sql.Request();
                            // query to the database
                            request.query(query, function (err, data) {
                                if (err) {
                                    console.log("Error while querying database :- " + err);
                                    res.send(err);
                                }
                                else {
                                    res.send(data);
                                    //console.log("response.body: " + res.body);
                                    //console.log(res.recordset[0].UserName);
                                }
                            });
                        }
                    });
                });

                // DELETE API
                app.delete("/api/admin/user/:id", function (req, res) {
                    var query = "DELETE FROM [AdminLogIn] WHERE Id=" + req.params.id;
                    sql.connect(dbConfig, function (err) {
                        if (err) {
                            console.log("Error while connecting database :- " + err);
                            res.send(err);
                        }
                        else {
                            //Connection success notification
                            console.log("Successfully connected to the " + dbConfig.database);
                            //Connection success notification


                            // create Request object
                            var request = new sql.Request();
                            // query to the database
                            request.query(query, function (err, data) {
                                if (err) {
                                    console.log("Error while querying database :- " + err);
                                    res.send(err);
                                }
                                else {
                                    res.send(data);
                                    //console.log("response.body: " + res.body);
                                    //console.log(res.recordset[0].UserName);
                                }
                            });
                        }
                    });
                });


                                    //   ___      ___  ______    ___       ____  ____  _____  ___  ___________  _______   _______   _______   
                                    //  |"  \    /"  |/    " \  |"  |     ("  _||_ " |(\"   \|"  \("     _   ")/"     "| /"     "| /"      \  
                                    //   \   \  //  /// ____  \ ||  |     |   (  ) : ||.\\   \    |)__/  \\__/(: ______)(: ______)|:        | 
                                    //    \\  \/. .//  /    ) :)|:  |     (:  |  | . )|: \.   \\  |   \\_ /    \/    |   \/    |  |_____/   ) 
                                    //     \.    //(: (____/ //  \  |___   \\ \__/ // |.  \    \. |   |.  |    // ___)_  // ___)_  //      /  
                                    //      \\   /  \        /  ( \_|:  \  /\\ __ //\ |    \    \ |   \:  |   (:      "|(:      "||:  __   \  
                                    //       \__/    \"_____/    \_______)(__________) \___|\____\)    \__|    \_______) \_______)|__|  \___) 
                                    //       

              



                //GET API
                 app.get("/api/volunteer/login/android/:name/:pass", function (req, res) {
                    var query = "select VId from [VolunteerRegistration2] where UserName= '" + req.params.name + "' and Password= '" + req.params.pass +"'";
                     sql.connect(dbConfig, function (err) {
                        if (err) {
                            console.log("Error while connecting database :- " + err);
                            res.send(err);
                        }
                        else {
                            //Connection success notification
                            console.log("Successfully connected to the " + dbConfig.database);
                            //Connection success notification


                            // create Request object
                            var request = new sql.Request();
                            // query to the database
                            request.query(query, function (err, data) {
                                var checker = data.recordset[0];
                                if (err) {
                                    console.log("Error while querying database :- " + err);
                                    res.send(err);
                                }
                                else {
                                    if (checker !== undefined) {
                                        res.send("logged in successfully");
                                    } else {
                                        res.send("user not found");
                                    }
                                    console.log(checker);
                                    //console.log("response.body: " + res.body);
                                    //console.log(res.recordset[0].UserName);
                                }
                            });
                        }
                    });
                });



















		 //GET API
                app.get("/api/volunteer/user", function (req, res) {
                    var query = "select * from [VolunteerRegistration2] where aStatus='True' ORDER BY VName ASC";
                    sql.connect(dbConfig, function (err) {
                        if (err) {
                            console.log("Error while connecting database :- " + err);
                            res.send(err);
                        }
                        else {
                            //Connection success notification
                            console.log("Successfully connected to the " + dbConfig.database);
                            //Connection success notification


                            // create Request object
                            var request = new sql.Request();
                            // query to the database
                            request.query(query, function (err, data) {
                                if (err) {
                                    console.log("Error while querying database :- " + err);
                                    res.send(err);
                                }
                                else {
                                    var jsonData = {};
                                    jsonData.data = data.recordset;
                                    jsonData.success = "1";
                                    res.send(jsonData);
                                    //console.log("response.body: " + res.body);
                                    //console.log(res.recordset[0].UserName);
                                }
                            });
                        }
                    });
                });

                //POST API
                app.post("/api/volunteer/admin", function (req, res) {
                    var query = "INSERT INTO [VolunteerRegistration2] (VName, Address, Email, Gender, DateOfBirth, ContactNo, Profession, FromWhereYouLearnAboutUs, TheReasonOfYourVolunteering, Uimg, Blood, aStatus, UserName, Password, PracticeId) VALUES ('" + req.body.VName + "','" + req.body.Address + "','" + req.body.Email + "','" + req.body.Gender + "','" + req.body.DateOfBirth + "','" + req.body.ContactNo + "','" + req.body.Profession + "','" + req.body.FromWhereYouLearnAboutUs + "','" + req.body.TheReasonOfYourVolunteering + "','" + req.body.Uimg + "','" + req.body.Blood + "','True','0','0','0')";
                    sql.connect(dbConfig, function (err) {
                        if (err) {
                            console.log("Error while connecting database :- " + err);
                            res.send(err);
                        }
                        else {
                            //Connection success notification
                            console.log("Successfully connected to the " + dbConfig.database);
                            //Connection success notification


                            // create Request object
                            var request = new sql.Request();
                            // query to the database
                            request.query(query, function (err, data) {
                                if (err) {
                                    console.log("Error while querying database :- " + err);
                                    res.send("Failed");
                                }
                                else {
                                    res.send("Registration Successful");
                                    //console.log("response.body: " + res.body);
                                    //console.log(res.recordset[0].UserName);
                                }
                            });
                        }
                    });
                });



		//POST API
                app.post("/api/volunteer/user/registration", function (req, res) {
                    var query = "INSERT INTO [VolunteerRegistration2] (VName, Address, Email, Gender, DateOfBirth, ContactNo, Profession, FromWhereYouLearnAboutUs, TheReasonOfYourVolunteering, Uimg, Blood, aStatus, UserName, Password, PracticeId) VALUES ('" + req.body.VName + "','" + req.body.Address + "','" + req.body.Email + "','" + req.body.Gender + "','" + req.body.DateOfBirth + "','" + req.body.ContactNo + "','" + req.body.Profession + "','" + req.body.FromWhereYouLearnAboutUs + "','" + req.body.TheReasonOfYourVolunteering + "','" + req.body.Uimg + "','" + req.body.Blood + "','False','0','0','0')";
                    sql.connect(dbConfig, function (err) {
                        if (err) {
                            console.log("Error while connecting database :- " + err);
                            res.send(err);
                        }
                        else {
                            //Connection success notification
                            console.log("Successfully connected to the " + dbConfig.database);
                            //Connection success notification


                            // create Request object
                            var request = new sql.Request();
                            // query to the database
                            request.query(query, function (err, data) {
                                if (err) {
                                    console.log("Error while querying database :- " + err);
                                    res.send("Failed");
                                }
                                else {
                                    res.send("Registration Successful");
                                    //console.log("response.body: " + res.body);
                                    //console.log(res.recordset[0].UserName);
                                }
                            });
                        }
                    });
                });





                //POST API ANDROID
                app.post("/api/volunteer/user/android/post", function (req, res) {
                    var query = "INSERT INTO [VolunteerRegistration2] (VName, Address, Email, Gender, DateOfBirth, ContactNo, Profession, FromWhereYouLearnAboutUs, TheReasonOfYourVolunteering, Uimg, Blood, aStatus, UserName, Password, PracticeId) VALUES ('" + req.body.VName + "','" + req.body.Address + "','" + req.body.Email + "','" + req.body.Gender + "','" + req.body.DateOfBirth + "','" + req.body.ContactNo + "','" + req.body.Profession + "','" + req.body.FromWhereYouLearnAboutUs + "','" + req.body.TheReasonOfYourVolunteering + "','" + req.body.Uimg + "','" + req.body.Blood + "','" + req.body.aStatus + "','" + req.body.UserName + "','" + req.body.Password + "','" + req.body.PracticeId + "')";
                    sql.connect(dbConfig, function (err) {
                        if (err) {
                            console.log("Error while connecting database :- " + err);
                            res.send(err);
                        }
                        else {
                            //Connection success notification
                            console.log("Successfully connected to the " + dbConfig.database);
                            //Connection success notification


                            // create Request object
                            var request = new sql.Request();
                            // query to the database
                            request.query(query, function (err, data) {
                                if (err) {
                                    console.log("Error while querying database :- " + err);
                                    res.send("Registration Successful");
                                }
                                else {
                                    res.send("Failed");
                                    //console.log("response.body: " + res.body);
                                    //console.log(res.recordset[0].UserName);
                                }
                            });
                        }
                    });
                });


				//Edit Volunteer change krsi query jate password change na hoi
                //PUT API
                app.put("/api/volunteer/user/update", function (req, res) {
                 // var query = "UPDATE [VolunteerRegistration2] SET VName= '" + req.body.VName + "' , Address= '" + req.body.Address + "' , Email=  '" + req.body.Email + "' , Gender=  '" + req.body.Gender + "' , DateOfBirth=  '" + req.body.DateOfBirth + "' , ContactNo=  '" + req.body.ContactNo + "' , Profession=  '" + req.body.Profession + "' , FromWhereYouLearnAboutUs=  '" + req.body.FromWhereYouLearnAboutUs + "' , TheReasonOfYourVolunteering=  '" + req.body.TheReasonOfYourVolunteering + "' , Uimg=  '" + req.body.Uimg + "' , Blood=  '" + req.body.Blood + "' , aStatus=  'True' , UserName=  '0' , Password=  '0' , PracticeId=  '0'  WHERE VId= " + req.body.VId;
                     var query = "UPDATE [VolunteerRegistration2] SET VName= '" + req.body.VName + "' , Address= '" + req.body.Address + "' , Email=  '" + req.body.Email + "' , Gender=  '" + req.body.Gender + "' , DateOfBirth=  '" + req.body.DateOfBirth + "' , ContactNo=  '" + req.body.ContactNo + "' , Profession=  '" + req.body.Profession + "' , FromWhereYouLearnAboutUs=  '" + req.body.FromWhereYouLearnAboutUs + "' , TheReasonOfYourVolunteering=  '" + req.body.TheReasonOfYourVolunteering + "' , Uimg=  '" + req.body.Uimg + "' , Blood=  '" + req.body.Blood + "'   WHERE VId= " + req.body.VId;
					sql.connect(dbConfig, function (err) {
                        if (err) {
                            console.log("Error while connecting database :- " + err);
                            res.send(err);
                        }
                        else {
                            //Connection success notification
                            console.log("Successfully connected to the " + dbConfig.database);
                            //Connection success notification


                            // create Request object
                            var request = new sql.Request();
                            // query to the database
                            request.query(query, function (err, data) {
                                if (err) {
                                    console.log("Error while querying database :- " + err);
                                    res.send("Failed");
                                }
                                else {
                                    res.send("Data Updated");
                                    //console.log("response.body: " + res.body);
                                    //console.log(res.recordset[0].UserName);
                                }
                            });
                        }
                    });
                });

                // DELETE API
                app.post("/api/volunteer/user/android/delete/:id", function (req, res) {
                    var query = "DELETE FROM [VolunteerRegistration2] WHERE VId='" + req.params.id + "'";
                    sql.connect(dbConfig, function (err) {
                        if (err) {
                            console.log("Error while connecting database :- " + err);
                            res.send(err);
                        }
                        else {
                            //Connection success notification
                            console.log("Successfully connected to the " + dbConfig.database);
                            //Connection success notification


                            // create Request object
                            var request = new sql.Request();
                            // query to the database
                            request.query(query, function (err, data) {
                                if (err) {
                                    console.log("Error while querying database :- " + err);
                                    res.send("err");
                                }
                                else {
                                    res.send("deleted Successfully");
                                    //console.log("response.body: " + res.body);
                                    //console.log(res.recordset[0].UserName);
                                }
                            });
                        }
                    });
                });


                                    //      ______     _______     _______    __    __       __      _____  ___   
                                    //     /    " \   /"      \   |   __ "\  /" |  | "\     /""\    (\"   \|"  \  
                                    //    // ____  \ |:        |  (. |__) :)(:  (__)  :)   /    \   |.\\   \    | 
                                    //   /  /    ) :)|_____/   )  |:  ____/  \/      \/   /' /\  \  |: \.   \\  | 
                                    //  (: (____/ //  //      /   (|  /      //  __  \\  //  __'  \ |.  \    \. | 
                                    //   \        /  |:  __   \  /|__/ \    (:  (  )  :)/   /  \\  \|    \    \ | 
                                    //    \"_____/   |__|  \___)(_______)    \__|  |__/(___/    \___)\___|\____\) 
                                    //                                                                            

                //GET API
                app.get("/api/orphan/user", function (req, res) {
                    var query = "select * from [AdmitOrphan2] ORDER BY Name ASC";
                    sql.connect(dbConfig, function (err) {
                        if (err) {
                            console.log("Error while connecting database :- " + err);
                            res.send(err);
                        }
                        else {
                            //Connection success notification
                            console.log("Successfully connected to the " + dbConfig.database);
                            //Connection success notification


                            // create Request object
                            var request = new sql.Request();
                            // query to the database
                            request.query(query, function (err, data) {
                                if (err) {
                                    console.log("Error while querying database :- " + err);
                                    res.send(err);
                                }
                                else {
                                    var jsonData = {};
                                    jsonData.data = data.recordset;
                                    jsonData.success = "1";
                                    res.send(jsonData);
                                    //console.log("response.body: " + res.body);
                                    //console.log(res.recordset[0].UserName);
                                }
                            });
                        }
                    });
                });

                ////POST API
                //app.post("/api/orphan/user/android/post/:nm/:rage/:hist/:gen/:pd/:dkind/:mstatus/:agecat/:uimg/:blood", function (req, res) {
                //    var catRng2Int;
                //    if (req.params.agecat === "3 to 8") {
                //        catRng2Int = 1;
                //    } else if (req.params.agecat === "9 to 12") {
                //        catRng2Int = 2;
                //    } else if (req.params.agecat === "13 to 15") {
                //        catRng2Int = 3;
                //    }
                //    var query = "INSERT INTO [AdmitOrphan2] (Name,RealAge,History,Gender,PhysicalDefect,WhatKindoFPhysicalDefect,MentalStatus,AgeCategoryId,Uimg,Blood) VALUES ('" + req.params.nm + "','" + req.params.rage + "','" + req.params.hist + "','" + req.params.gen + "','" + req.params.pd + "','" + req.params.dkind + "','" + req.params.mstatus + "','" + catRng2Int + "','" + req.params.uimg + "','" + req.params.blood + "')";
                //    sql.connect(dbConfig, function (err) {
                //        if (err) {
                //            console.log("Error while connecting database :- " + err);
                //            res.send(err);
                //        }
                //        else {
                //            //Connection success notification
                //            console.log("Successfully connected to the " + dbConfig.database);
                //            //Connection success notification


                //            // create Request object
                //            var request = new sql.Request();
                //            // query to the database
                //            request.query(query, function (err, data) {
                //                if (err) {
                //                    console.log("Error while querying database :- " + err);
                //                    res.send("failure");
                //                }
                //                else {
                //                    res.send("Data Inserted");
                //                    //console.log("response.body: " + res.body);
                //                    //console.log(res.recordset[0].UserName);
                //                }
                //            });
                //        }
                //    });
                //});

                //POST API
                app.post("/api/orphan/user/android/post/", function (req, res) {
                    //var catRng2Int = 0;
                    //if (req.body.agecat === "3 to 8") {
                    //    catRng2Int = 1;
                    //} else if (req.body.agecat === "9 to 12") {
                    //    catRng2Int = 2;
                    //} else if (req.body.agecat === "13 to 15") {
                    //    catRng2Int = 3;
                    //}
                    var query = "INSERT INTO [AdmitOrphan2] (Name,RealAge,History,Gender,PhysicalDefect,WhatKindoFPhysicalDefect,MentalStatus,AgeCategoryId,Uimg,Blood) VALUES ('" + req.body.Name + "','" + req.body.RealAge + "','" + req.body.History + "','" + req.body.Gender + "','" + req.body.PhysicalDefect + "','" + req.body.WhatKindoFPhysicalDefect + "','" + req.body.MentalStatus + "','" + parseInt(req.body.AgeCategoryId) + "','" + req.body.Uimg + "','" + req.body.Blood + "')";
                    sql.connect(dbConfig, function (err) {
                        if (err) {
                            console.log("Error while connecting database :- " + err);
                            res.send(err);
                        }
                        else {
                            //Connection success notification
                            console.log("Successfully connected to the " + dbConfig.database);
                            //Connection success notification
                            // create Request object
                            var request = new sql.Request();
                            // query to the database
                            request.query(query, function (err, data) {
                                if (err) {
                                    console.log("Error while querying database :- " + err);
                                    res.send("failure");
                                }
                                else {
                                    res.send("Data Inserted");
                                    //console.log(res.recordset[0].UserName);
                                }
                            });
                        }
                    });
                });

                //PUT API
                app.put("/api/orphan/user/update", function (req, res) {
                    var query = "UPDATE [AdmitOrphan2] SET Name= '" + req.body.Name + "' , RealAge= '" + req.body.RealAge + "' , History= '" + req.body.History + "' , Gender= '" + req.body.Gender + "' , PhysicalDefect= '" + req.body.PhysicalDefect + "' , WhatKindoFPhysicalDefect= '" + req.body.WhatKindoFPhysicalDefect + "' , MentalStatus= '" + req.body.MentalStatus + "' , AgeCategoryId= '" + req.body.AgeCategoryId + "' , Uimg= '" + req.body.Uimg + "' , Blood=  '" + req.body.Blood + "'  WHERE Id= '" + req.body.Id +"'";
                    sql.connect(dbConfig, function (err) {
                        if (err) {
                            console.log("Error while connecting database :- " + err);
                            res.send(err);
                        }
                        else {
                            //Connection success notification
                            console.log("Successfully connected to the " + dbConfig.database);
                            //Connection success notification


                            // create Request object
                            var request = new sql.Request();
                            // query to the database
                            request.query(query, function (err, data) {
                                if (err) {
                                    console.log("Error while querying database :- " + err);
                                    res.send("Failed");
                                }
                                else {
                                    res.send("Data Updated");
                                    //console.log("response.body: " + res.body);
                                    //console.log(res.recordset[0].UserName);
                                }
                            });
                        }
                    });
                });

                // DELETE API
                app.post("/api/orphan/user/android/delete/:id", function (req, res) {
                    var query = "DELETE FROM [AdmitOrphan2] WHERE Id='" + req.params.id + "'";
                    sql.connect(dbConfig, function (err) {
                        if (err) {
                            console.log("Error while connecting database :- " + err);
                            res.send(err);
                        }
                        else {
                            //Connection success notification
                            console.log("Successfully connected to the " + dbConfig.database);
                            //Connection success notification


                            // create Request object
                            var request = new sql.Request();
                            // query to the database
                            request.query(query, function (err, data) {
                                if (err) {
                                    console.log("Error while querying database :- " + err);
                                    res.send("err");
                                }
                                else {
                                    res.send("deleted Successfully");
                                    //console.log("response.body: " + res.body);
                                    //console.log(res.recordset[0].UserName);
                                }
                            });
                        }
                    });
                });

                                    //    _______  ___      ___  _______  _____  ___  ___________  
                                    //   /"     "||"  \    /"  |/"     "|(\"   \|"  \("     _   ") 
                                    //  (: ______) \   \  //  /(: ______)|.\\   \    |)__/  \\__/  
                                    //   \/    |    \\  \/. ./  \/    |  |: \.   \\  |   \\_ /     
                                    //   // ___)_    \.    //   // ___)_ |.  \    \. |   |.  |     
                                    //  (:      "|    \\   /   (:      "||    \    \ |   \:  |     
                                    //   \_______)     \__/     \_______) \___|\____\)    \__|     
                                    //                                                             

                //GET API
                app.get("/api/event/user", function (req, res) {
                    var query = "SELECT TOP 6 * FROM Event ORDER BY Id DESC";
                    sql.connect(dbConfig, function (err) {
                        if (err) {
                            console.log("Error while connecting database :- " + err);
                            res.send(err);
                        }
                        else {
                            //Connection success notification
                            console.log("Successfully connected to the " + dbConfig.database);
                            //Connection success notification


                            // create Request object
                            var request = new sql.Request();
                            // query to the database
                            request.query(query, function (err, data) {
                                if (err) {
                                    console.log("Error while querying database :- " + err);
                                    res.send(err);
                                }
                                else {
                                    var jsonData = {};
                                    jsonData.data = data.recordset;
                                    jsonData.success = "1";
                                    res.send(jsonData);
                                    //console.log("response.body: " + res.body);
                                    //console.log(res.recordset[0].UserName);
                                }
                            });
                        }
                    });
                });

                //POST API
                app.post("/api/event/user", function (req, res) {
                    var query = "INSERT INTO [Event] (Title,Date,Time,Venu,WriteSomething,Image) VALUES ('" + req.body.Title + "','" + req.body.Date + "','" + req.body.Time + "','" + req.body.Venu + "','" + req.body.WriteSomething + "','" + req.body.Image + "')";
                    sql.connect(dbConfig, function (err) {
                        if (err) {
                            console.log("Error while connecting database :- " + err);
                            res.send(err);
                        }
                        else {
                            //Connection success notification
                            console.log("Successfully connected to the " + dbConfig.database);
                            //Connection success notification


                            // create Request object
                            var request = new sql.Request();
                            // query to the database
                            request.query(query, function (err, data) {
                                if (err) {
                                    console.log("Error while querying database :- " + err);
                                    res.send("failure");
                                }
                                else {
                                    res.send("success");
                                    //console.log("response.body: " + res.body);
                                    //console.log(res.recordset[0].UserName);
                                }
                            });
                        }
                    });
                });

                //PUT API
                app.put("/api/event/user/:id", function (req, res) {
                    var query = "UPDATE [Event] SET Title= '" + req.body.Title + "' , Date=  '" + req.body.Date + "'  WHERE Id= " + req.params.id;
                    sql.connect(dbConfig, function (err) {
                        if (err) {
                            console.log("Error while connecting database :- " + err);
                            res.send(err);
                        }
                        else {
                            //Connection success notification
                            console.log("Successfully connected to the " + dbConfig.database);
                            //Connection success notification


                            // create Request object
                            var request = new sql.Request();
                            // query to the database
                            request.query(query, function (err, data) {
                                if (err) {
                                    console.log("Error while querying database :- " + err);
                                    res.send(err);
                                }
                                else {
                                    res.send(data);
                                    //console.log("response.body: " + res.body);
                                    //console.log(res.recordset[0].UserName);
                                }
                            });
                        }
                    });
                });

                // DELETE API
                app.delete("/api/event/user/:id", function (req, res) {
                    var query = "DELETE FROM [Event] WHERE Id=" + req.params.id;
                    sql.connect(dbConfig, function (err) {
                        if (err) {
                            console.log("Error while connecting database :- " + err);
                            res.send(err);
                        }
                        else {
                            //Connection success notification
                            console.log("Successfully connected to the " + dbConfig.database);
                            //Connection success notification


                            // create Request object
                            var request = new sql.Request();
                            // query to the database
                            request.query(query, function (err, data) {
                                if (err) {
                                    console.log("Error while querying database :- " + err);
                                    res.send(err);
                                }
                                else {
                                    res.send(data);
                                    //console.log("response.body: " + res.body);
                                    //console.log(res.recordset[0].UserName);
                                }
                            });
                        }
                    });
                });


                                    //        __       ________      ______    _______  ___________  __      ______    _____  ___   
                                    //       /""\     |"      "\    /    " \  |   __ "\("     _   ")|" \    /    " \  (\"   \|"  \  
                                    //      /    \    (.  ___  :)  // ____  \ (. |__) :))__/  \\__/ ||  |  // ____  \ |.\\   \    | 
                                    //     /' /\  \   |: \   ) || /  /    ) :)|:  ____/    \\_ /    |:  | /  /    ) :)|: \.   \\  | 
                                    //    //  __'  \  (| (___\ ||(: (____/ // (|  /        |.  |    |.  |(: (____/ // |.  \    \. | 
                                    //   /   /  \\  \ |:       :) \        / /|__/ \       \:  |    /\  |\\        /  |    \    \ | 
                                    //  (___/    \___)(________/   \"_____/ (_______)       \__|   (__\_|_)\"_____/    \___|\____\) 
                                    //                                                                                              

                //GET API
                app.get("/api/adoption/user", function (req, res) {
                    var query = "select * from [Adoption]";
                    sql.connect(dbConfig, function (err) {
                        if (err) {
                            console.log("Error while connecting database :- " + err);
                            res.send(err);
                        }
                        else {
                            //Connection success notification
                            console.log("Successfully connected to the " + dbConfig.database);
                            //Connection success notification


                            // create Request object
                            var request = new sql.Request();
                            // query to the database
                            request.query(query, function (err, data) {
                                if (err) {
                                    console.log("Error while querying database :- " + err);
                                    res.send(err);
                                }
                                else {
                                    res.send(data);
                                    //console.log("response.body: " + res.body);
                                    //console.log(res.recordset[0].UserName);
                                }
                            });
                        }
                    });
                });

                //POST API
                app.post("/api/adoption/user", function (req, res) {
                    var query = "INSERT INTO [Adoption] (AName, Gender, Email, Phone, PresentAddress, ParmanentAddress, Proffession, MonthlySalary, NID, Blood, Children, Uimg, Reason, AdoptAnyChild, aStatus, OrphanName) VALUES ('" + req.body.AName + "','" + req.body.Gender + "','" + req.body.Email + "','" + req.body.Phone + "','" + req.body.PresentAddress + "','" + req.body.ParmanentAddress + "','" + req.body.Proffession + "','" + req.body.MonthlySalary + "','" + req.body.NID + "','" + req.body.Blood + "','" + req.body.Children + "','" + req.body.Uimg + "','" + req.body.Reason + "','" + req.body.AdoptAnyChild + "','" + req.body.aStatus + "','" + req.body.OrphanName + "')";
                    sql.connect(dbConfig, function (err) {
                        if (err) {
                            console.log("Error while connecting database :- " + err);
                            res.send(err);
                        }
                        else {
                            //Connection success notification
                            console.log("Successfully connected to the " + dbConfig.database);
                            //Connection success notification


                            // create Request object
                            var request = new sql.Request();
                            // query to the database
                            request.query(query, function (err, data) {
                                if (err) {
                                    console.log("Error while querying database :- " + err);
                                    res.send(err);
                                }
                                else {
                                    res.send(data);
                                    //console.log("response.body: " + res.body);
                                    //console.log(res.recordset[0].UserName);
                                }
                            });
                        }
                    });
                });

                //PUT API
                app.put("/api/adoption/user/update", function (req, res) {
                    var query = "UPDATE [Adoption] SET AName= '" + req.body.AName + "' , Gender= '" + req.body.Gender + "' , Email= '" + req.body.Email + "' , Phone= '" + req.body.Phone + "' , PresentAddress= '" + req.body.PresentAddress + "' , ParmanentAddress= '" + req.body.ParmanentAddress + "' , Proffession= '" + req.body.Proffession + "' , MonthlySalary= '" + req.body.MonthlySalary + "' , NID= '" + req.body.NID + "' , Blood= '" + req.body.Blood + "' , Children= '" + req.body.Children + "' , Uimg= '" + req.body.Uimg + "' , Reason= '" + req.body.Reason + "' , AdoptAnyChild= '" + req.body.AdoptAnyChild + "' , aStatus= '" + req.body.aStatus + "' , OrphanName=  '" + req.body.OrphanName + "'  WHERE AId= " + req.body.AId;
                    sql.connect(dbConfig, function (err) {
                        if (err) {
                            console.log("Error while connecting database :- " + err);
                            res.send(err);
                        }
                        else {
                            //Connection success notification
                            console.log("Successfully connected to the " + dbConfig.database);
                            //Connection success notification


                            // create Request object
                            var request = new sql.Request();
                            // query to the database
                            request.query(query, function (err, data) {
                                if (err) {
                                    console.log("Error while querying database :- " + err);
                                    res.send("Failed");
                                }
                                else {
                                    res.send("Data Updated");
                                    //console.log("response.body: " + res.body);
                                    //console.log(res.recordset[0].UserName);
                                }
                            });
                        }
                    });
                });

                // DELETE API
                app.delete("/api/adoption/user/:id", function (req, res) {
                    var query = "DELETE FROM [Adoption] WHERE AId=" + req.params.id;
                    sql.connect(dbConfig, function (err) {
                        if (err) {
                            console.log("Error while connecting database :- " + err);
                            res.send(err);
                        }
                        else {
                            //Connection success notification
                            console.log("Successfully connected to the " + dbConfig.database);
                            //Connection success notification


                            // create Request object
                            var request = new sql.Request();
                            // query to the database
                            request.query(query, function (err, data) {
                                if (err) {
                                    console.log("Error while querying database :- " + err);
                                    res.send(err);
                                }
                                else {
                                    res.send(data);
                                    //console.log("response.body: " + res.body);
                                    //console.log(res.recordset[0].UserName);
                                }
                            });
                        }
                    });
                });


                                                            //    ______            _ ____             _    
                                                            //   |  ____|          | |  _ \           | |   
                                                            //   | |__ ___  ___  __| | |_) | __ _  ___| | __
                                                            //   |  __/ _ \/ _ \/ _` |  _ < / _` |/ __| |/ /
                                                            //   | | |  __/  __/ (_| | |_) | (_| | (__|   < 
                                                            //   |_|  \___|\___|\__,_|____/ \__,_|\___|_|\_\
                                                            //                                              
                                                            //                                              



                //get API tasfia
                app.get("/api/feedback/user/android/get", function (req, res) {
                    var query = "select * from [Feedback]";
                    sql.connect(dbConfig, function (err) {
                        if (err) {
                            console.log("Error while connecting database :- " + err);
                            res.send(err);
                        }
                        else {
                            //Connection success notification
                            console.log("Successfully connected to the " + dbConfig.database);
                            //Connection success notification


                            // create Request object
                            var request = new sql.Request();
                            // query to the database
                            request.query(query, function (err, data) {
                                if (err) {
                                    console.log("Error while querying database :- " + err);
                                    res.send(err);
                                }
                                else {
                                    var jsonData = {};
                                    jsonData.data = data.recordset;
                                    jsonData.success = "1";
                                    res.send(jsonData);
                                    //console.log("response.body: " + res.body);
                                    //console.log(res.recordset[0].UserName);
                                }
                            });
                        }
                    });
                });


                //POST API
                app.post("/api/feedback/user/android/post", function (req, res) {
                    var query = "INSERT INTO [Feedback] (Name, Email, Comments) VALUES ('" + req.body.Name + "','" + req.body.Email + "','" + req.body.Comments + "')";
                    sql.connect(dbConfig, function (err) {
                        if (err) {
                            console.log("Error while connecting database :- " + err);
                            res.send(err);
                        }
                        else {
                            //Connection success notification
                            console.log("Successfully connected to the " + dbConfig.database);
                            //Connection success notification


                            // create Request object
                            var request = new sql.Request();
                            // query to the database
                            request.query(query, function (err, data) {
                                if (err) {
                                    console.log("Error while querying database :- " + err);
                                    res.send("failure");
                                }
                                else {
                                    res.send("success");
                                    //console.log("response.body: " + res.body);
                                    //console.log(res.recordset[0].UserName);
                                }
                            });
                        }
                    });
                });



                                                        //    _____                    _             
                                                        //   |  __ \                  | |            
                                                        //   | |  | | ___  _ __   __ _| |_ ___  _ __ 
                                                        //   | |  | |/ _ \| '_ \ / _` | __/ _ \| '__|
                                                        //   | |__| | (_) | | | | (_| | || (_) | |   
                                                        //   |_____/ \___/|_| |_|\__,_|\__\___/|_|   
                                                        //                                           
                                                        //                                           

                //GET API
                app.get("/api/dreg/user/android/get", function (req, res) {
                    var query = "select * from [DReg] where Status='True' ORDER BY Name ASC";
                    sql.connect(dbConfig, function (err) {
                        if (err) {
                            console.log("Error while connecting database :- " + err);
                            res.send(err);
                        }
                        else {
                            //Connection success notification
                            console.log("Successfully connected to the " + dbConfig.database);
                            //Connection success notification


                            // create Request object
                            var request = new sql.Request();
                            // query to the database
                            request.query(query, function (err, data) {
                                if (err) {
                                    console.log("Error while querying database :- " + err);
                                    res.send(err);
                                }
                                else {
                                    var jsonData = {};
                                    jsonData.data = data.recordset;
                                    jsonData.success = "1";
                                    res.send(jsonData);
                                }
                            });
                        }
                    });
                });


                //POST API
                app.post("/api/dreg/user/android/post", function (req, res) {
                    var query = "INSERT INTO [DReg] (Name,Gender,Address,City,Profession,ContactNo,Email,Status,UserName,Password,PracticeId) VALUES ('" + req.body.Name + "','" + req.body.Gender + "','" + req.body.Address + "','" + req.body.City + "','" + req.body.Profession + "','" + req.body.ContactNo + "','" + req.body.Email + "','True','0','0','0')";
                    sql.connect(dbConfig, function (err) {
                        if (err) {
                            console.log("Error while connecting database :- " + err);
                            res.send(err);
                        }
                        else {
                            //Connection success notification
                            console.log("Successfully connected to the " + dbConfig.database);
                            //Connection success notification


                            // create Request object
                            var request = new sql.Request();
                            // query to the database
                            request.query(query, function (err, data) {
                                if (err) {
                                    console.log("Error while querying database :- " + err);
                                    res.send("failure");
                                }
                                else {
                                    res.send("success");
                                }
                            });
                        }
                    });
                });


                // DELETE API
                app.post("/api/dreg/user/android/delete/:id", function (req, res) {
                    var query = "DELETE FROM [DReg] WHERE Id='"+req.params.id+"' and Status='True'";
                    sql.connect(dbConfig, function (err) {
                        if (err) {
                            console.log("Error while connecting database :- " + err);
                            res.send(err);
                        }
                        else {
                            //Connection success notification
                            console.log("Successfully connected to the " + dbConfig.database);
                            //Connection success notification


                            // create Request object
                            var request = new sql.Request();
                            // query to the database
                            request.query(query, function (err, data) {
                                if (err) {
                                    console.log("Error while querying database :- " + err);
                                    res.send("err");
                                }
                                else {
                                    res.send("deleted Successfully");
                                    //console.log("response.body: " + res.body);
                                    //console.log(res.recordset[0].UserName);
                                }
                            });
                        }
                    });
                });




		  //POST API
                app.post("/api/dreg/permanentuser/android/post", function (req, res) {
                    var query = "INSERT INTO [DReg] (Name,Gender,Address,City,Profession,ContactNo,Email,Status,UserName,Password,PracticeId) VALUES ('" + req.body.Name + "','" + req.body.Gender + "','" + req.body.Address + "','" + req.body.City + "','" + req.body.Profession + "','" + req.body.ContactNo + "','" + req.body.Email + "','False','0','0','0')";
                    sql.connect(dbConfig, function (err) {
                        if (err) {
                            console.log("Error while connecting database :- " + err);
                            res.send(err);
                        }
                        else {
                            //Connection success notification
                            console.log("Successfully connected to the " + dbConfig.database);
                            //Connection success notification


                            // create Request object
                            var request = new sql.Request();
                            // query to the database
                            request.query(query, function (err, data) {
                                if (err) {
                                    console.log("Error while querying database :- " + err);
                                    res.send("failure");
                                }
                                else {
                                    res.send("success");
                                }
                            });
                        }
                    });
                });










		//nije try kori

		//Volunteer Notice


		//GET API
                app.get("/api/notice/volunteer", function (req, res) {
                    var query = "SELECT TOP 6 * FROM ANotice ORDER BY NId DESC";
                    sql.connect(dbConfig, function (err) {
                        if (err) {
                            console.log("Error while connecting database :- " + err);
                            res.send(err);
                        }
                        else {
                            //Connection success notification
                            console.log("Successfully connected to the " + dbConfig.database);
                            //Connection success notification


                            // create Request object
                            var request = new sql.Request();
                            // query to the database
                            request.query(query, function (err, data) {
                                if (err) {
                                    console.log("Error while querying database :- " + err);
                                    res.send(err);
                                }
                                else {
                                    var jsonData = {};
                                    jsonData.data = data.recordset;
                                    jsonData.success = "1";
                                    res.send(jsonData);
                                    //console.log("response.body: " + res.body);
                                    //console.log(res.recordset[0].UserName);
                                }
                            });
                        }
                    });
                });


			//Donator LogIn

			 //GET API
                 app.get("/api/donator/login/android/:name/:pass", function (req, res) {
                    var query = "select Id from [DReg] where UserName= '" + req.params.name + "' and Password= '" + req.params.pass +"'";
                     sql.connect(dbConfig, function (err) {
                        if (err) {
                            console.log("Error while connecting database :- " + err);
                            res.send(err);
                        }
                        else {
                            //Connection success notification
                            console.log("Successfully connected to the " + dbConfig.database);
                            //Connection success notification


                            // create Request object
                            var request = new sql.Request();
                            // query to the database
                            request.query(query, function (err, data) {
                                var checker = data.recordset[0];
                                if (err) {
                                    console.log("Error while querying database :- " + err);
                                    res.send(err);
                                }
                                else {
                                    if (checker !== undefined) {
                                        res.send("logged in successfully");
                                    } else {
                                        res.send("user not found");
                                    }
                                    console.log(checker);
                                    //console.log("response.body: " + res.body);
                                    //console.log(res.recordset[0].UserName);
                                }
                            });
                        }
                    });
                });




			//volunteer Profile

			//GET API
                app.get("/api/volunteer/profile/:name/:pass", function (req, res) {
                var query = "SELECT  VName , Address, Email, ContactNo, Profession, Blood, Uimg  FROM  [VolunteerRegistration2]  WHERE UserName='" + req.params.name + "' and Password='" + req.params.pass + "'";
				sql.connect(dbConfig, function (err) {
                        if (err) {
                            console.log("Error while connecting database :- " + err);
                            res.send(err);
                        }
                        else {
                            //Connection success notification
                            console.log("Successfully connected to the " + dbConfig.database);
                            //Connection success notification


                            // create Request object
                            var request = new sql.Request();
                            // query to the database
                            request.query(query, function (err, data) {
                                if (err) {
                                    console.log("Error while querying database :- " + err);
                                    res.send(err);
                                }
                                else {
                                    var jsonData = {};
                                    jsonData.data = data.recordset;
                                    jsonData.success = "1";
                                    res.send(jsonData);
                                    //console.log("response.body: " + res.body);
                                    //console.log(res.recordset[0].UserName);
                                }
                            });
                        }
                    });
                });









//donator Profile

 //GET API
                app.get("/api/donator/profile/:name/:pass", function (req, res) {
                
		
                    
		var query = "SELECT  Name , Address, Email, ContactNo,City, Profession FROM  [DReg]  WHERE Username='" + req.params.name + "' and Password='" + req.params.pass + "'";
   
			
			  sql.connect(dbConfig, function (err) {
                        if (err) {
                            console.log("Error while connecting database :- " + err);
                            res.send(err);
                        }
                        else {
                            //Connection success notification
                            console.log("Successfully connected to the " + dbConfig.database);
                            //Connection success notification


                            // create Request object
                            var request = new sql.Request();
                            // query to the database
                            request.query(query, function (err, data) {
                                if (err) {
                                    console.log("Error while querying database :- " + err);
                                    res.send(err);
                                }
                                else {
                                    var jsonData = {};
                                    jsonData.data = data.recordset;
                                    jsonData.success = "1";
                                    res.send(jsonData);
                                    //console.log("response.body: " + res.body);
                                    //console.log(res.recordset[0].UserName);
                                }
                            });
                        }
                    });
                });










	//Admin Profile

 //GET API
                app.get("/api/admin/profile/:email", function (req, res) {
                
		
                    
		var query = "SELECT  UserName , Email, Skills,Education FROM  [AdminLogIn] WHERE Email='" + req.params.email + "'";
   
			
			  sql.connect(dbConfig, function (err) {
                        if (err) {
                            console.log("Error while connecting database :- " + err);
                            res.send(err);
                        }
                        else {
                            //Connection success notification
                            console.log("Successfully connected to the " + dbConfig.database);
                            //Connection success notification


                            // create Request object
                            var request = new sql.Request();
                            // query to the database
                            request.query(query, function (err, data) {
                                if (err) {
                                    console.log("Error while querying database :- " + err);
                                    res.send(err);
                                }
                                else {
                                    var jsonData = {};
                                    jsonData.data = data.recordset;
                                    jsonData.success = "1";
                                    res.send(jsonData);
                                    //console.log("response.body: " + res.body);
                                    //console.log(res.recordset[0].UserName);
                                }
                            });
                        }
                    });
                });












			//volunteer profile update
			//PUT API
				//app.put("/api/volunteer/profile/update/:VName/:Address/:Email/:ContactNo/:Profession/:Uimg/:UserName/:Password", function (req, res) {
                app.put("/api/volunteer/profile/update/", function (req, res) {
				//app.put("/api/volunteer/profile/update/", function (req, res) {
                    var query = "UPDATE [VolunteerRegistration2] SET VName= '" + req.body.VName + "' , Address= '" + req.body.Address + "' , Email=  '" + req.body.Email + "' ,  ContactNo=  '" + req.body.ContactNo + "' , Profession=  '" + req.body.Profession + "' ,  Uimg=  '" + req.body.Uimg + "'    WHERE UserName='" + req.body.UserName + "'";
                   // and  Password='" + req.params.Password + "'
				   sql.connect(dbConfig, function (err) {
                        if (err) {
                            console.log("Error while connecting database :- " + err);
                            res.send(err);
                        }
                        else {
                            //Connection success notification
                            console.log("Successfully connected to the " + dbConfig.database);
                            //Connection success notification


                            // create Request object
                            var request = new sql.Request();
                            // query to the database
                            request.query(query, function (err, data) {
                                if (err) {
                                    console.log("Error while querying database :- " + err);
                                    res.send("Failed");
                                }
                                else {
                                    res.send("Data Updated");
                                    //console.log("response.body: " + res.body);
                                    //console.log(res.recordset[0].UserName);
                                }
                            });
                        }
                    });
                });
				
				
				
				
				
				
				
				
				
				
				
			//donator profile update
			//PUT API
				
                app.put("/api/donator/profile/update/:Name/:Address/:Email/:ContactNo/:Profession/:UserName/:Password", function (req, res) {
				var query = "UPDATE [DReg] SET Name= '" + req.params.Name + "' , Address= '" + req.params.Address + "' , Email=  '" + req.params.Email + "' ,  ContactNo=  '" + req.params.ContactNo + "' , Profession=  '" + req.params.Profession + "'     WHERE UserName='" + req.params.UserName + "' and  Password='" + req.params.Password + "' ";
                sql.connect(dbConfig, function (err) {
                        if (err) {
                            console.log("Error while connecting database :- " + err);
                            res.send(err);
                        }
                        else {
                            //Connection success notification
                            console.log("Successfully connected to the " + dbConfig.database);
                            //Connection success notification


                            // create Request object
                            var request = new sql.Request();
                            // query to the database
                            request.query(query, function (err, data) {
                                if (err) {
                                    console.log("Error while querying database :- " + err);
                                    res.send("Failed");
                                }
                                else {
                                    res.send("Data Updated");
                                    //console.log("response.body: " + res.body);
                                    //console.log(res.recordset[0].UserName);
                                }
                            });
                        }
                    });
                });
				
				
				
				
				
				
				
				
				
				
				
				//admin profile update
			//PUT API
				
                app.put("/api/admin/profile/update/:UserName/:Skills/:Education/:Email/:Password", function (req, res) {
				var query = "UPDATE [AdminLogIn] SET UserName= '" + req.params.UserName + "'  ,  Skills=  '" + req.params.Skills + "' , Education=  '" + req.params.Education + "'     WHERE   Email='" + req.params.Email + "' and aPassword='" + req.params.Password + "' ";
                sql.connect(dbConfig, function (err) {
                        if (err) {
                            console.log("Error while connecting database :- " + err);
                            res.send(err);
                        }
                        else {
                            //Connection success notification
                            console.log("Successfully connected to the " + dbConfig.database);
                            //Connection success notification


                            // create Request object
                            var request = new sql.Request();
                            // query to the database
                            request.query(query, function (err, data) {
                                if (err) {
                                    console.log("Error while querying database :- " + err);
                                    res.send("Failed");
                                }
                                else {
                                    res.send("Data Updated");
                                    //console.log("response.body: " + res.body);
                                    //console.log(res.recordset[0].UserName);
                                }
                            });
                        }
                    });
                });
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				//Donation History
				 //GET API
                app.get("/api/donation/history/android/get/:name", function (req, res) {
                    var query = "select * from [DonationInfo] WHERE UserName='" + req.params.name + "'";
                    sql.connect(dbConfig, function (err) {
                        if (err) {
                            console.log("Error while connecting database :- " + err);
                            res.send(err);
                        }
                        else {
                            //Connection success notification
                            console.log("Successfully connected to the " + dbConfig.database);
                            //Connection success notification


                            // create Request object
                            var request = new sql.Request();
                            // query to the database
                            request.query(query, function (err, data) {
                                if (err) {
                                    console.log("Error while querying database :- " + err);
                                    res.send(err);
                                }
                                else {
                                    var jsonData = {};
                                    jsonData.data = data.recordset;
                                    jsonData.success = "1";
                                    res.send(jsonData);
                                }
                            });
                        }
                    });
                });
				
				
				
				
				
				
				
				//Volunteer Assign Work
				 //GET API
                app.get("/api/assignwork/volunteer/android/get/:name", function (req, res) {
                    var query = "select * from [VolunteerAssignWork] WHERE UserName='" + req.params.name + "'";
                    sql.connect(dbConfig, function (err) {
                        if (err) {
                            console.log("Error while connecting database :- " + err);
                            res.send(err);
                        }
                        else {
                            //Connection success notification
                            console.log("Successfully connected to the " + dbConfig.database);
                            //Connection success notification


                            // create Request object
                            var request = new sql.Request();
                            // query to the database
                            request.query(query, function (err, data) {
                                if (err) {
                                    console.log("Error while querying database :- " + err);
                                    res.send(err);
                                }
                                else {
                                    var jsonData = {};
                                    jsonData.data = data.recordset;
                                    jsonData.success = "1";
                                    res.send(jsonData);
                                }
                            });
                        }
                    });
                });
				
				
				
				
				
				
				// Donator er all info item soho ja admin dekhbe
				//GET API
                app.get("/api/donationallinfo/admin", function (req, res) {
                    var query = "select * from [DonationPayment] ORDER BY Time DESC";
                    sql.connect(dbConfig, function (err) {
                        if (err) {
                            console.log("Error while connecting database :- " + err);
                            res.send(err);
                        }
                        else {
                            //Connection success notification
                            console.log("Successfully connected to the " + dbConfig.database);
                            //Connection success notification


                            // create Request object
                            var request = new sql.Request();
                            // query to the database
                            request.query(query, function (err, data) {
                                if (err) {
                                    console.log("Error while querying database :- " + err);
                                    res.send(err);
                                }
                                else {
                                    var jsonData = {};
                                    jsonData.data = data.recordset;
                                    jsonData.success = "1";
                                    res.send(jsonData);
                                    //console.log("response.body: " + res.body);
                                    //console.log(res.recordset[0].UserName);
                                }
                            });
                        }
                    });
                });
				
				
				
				
				
				
				
				
				
				// Sponsorship Details ja admin dekhbe
				//GET API
                app.get("/api/sponsordetails/admin", function (req, res) {
                    var query = "select * from [SponsorshipHistory]";
                    sql.connect(dbConfig, function (err) {
                        if (err) {
                            console.log("Error while connecting database :- " + err);
                            res.send(err);
                        }
                        else {
                            //Connection success notification
                            console.log("Successfully connected to the " + dbConfig.database);
                            //Connection success notification


                            // create Request object
                            var request = new sql.Request();
                            // query to the database
                            request.query(query, function (err, data) {
                                if (err) {
                                    console.log("Error while querying database :- " + err);
                                    res.send(err);
                                }
                                else {
                                    var jsonData = {};
                                    jsonData.data = data.recordset;
                                    jsonData.success = "1";
                                    res.send(jsonData);
                                    //console.log("response.body: " + res.body);
                                    //console.log(res.recordset[0].UserName);
                                }
                            });
                        }
                    });
                });
				
				
				
				
				
				
				
				// Create notice ja admin create krbe
				//POST API
                app.post("/api/givenotice/admin", function (req, res) {
                    var query = "INSERT INTO [ANotice] (Information,Day,Time) VALUES ('" + req.body.Information + "','" + req.body.Day + "','" + req.body.Time + "')";
                    sql.connect(dbConfig, function (err) {
                        if (err) {
                            console.log("Error while connecting database :- " + err);
                            res.send(err);
                        }
                        else {
                            //Connection success notification
                            console.log("Successfully connected to the " + dbConfig.database);
                            //Connection success notification


                            // create Request object
                            var request = new sql.Request();
                            // query to the database
                            request.query(query, function (err, data) {
                                if (err) {
                                    console.log("Error while querying database :- " + err);
                                    res.send("failure");
                                }
                                else {
                                    res.send("success");
                                    //console.log("response.body: " + res.body);
                                    //console.log(res.recordset[0].UserName);
                                }
                            });
                        }
                    });
                });
				
				
				
				
				
				// View Notice admin ja dekhbe
				//GET API
                app.get("/api/allnotice/admin", function (req, res) {
                    var query = "SELECT * from [ANotice] ORDER BY NId DESC";
                    sql.connect(dbConfig, function (err) {
                        if (err) {
                            console.log("Error while connecting database :- " + err);
                            res.send(err);
                        }
                        else {
                            //Connection success notification
                            console.log("Successfully connected to the " + dbConfig.database);
                            //Connection success notification


                            // create Request object
                            var request = new sql.Request();
                            // query to the database
                            request.query(query, function (err, data) {
                                if (err) {
                                    console.log("Error while querying database :- " + err);
                                    res.send(err);
                                }
                                else {
                                    var jsonData = {};
                                    jsonData.data = data.recordset;
                                    jsonData.success = "1";
                                    res.send(jsonData);
                                    //console.log("response.body: " + res.body);
                                    //console.log(res.recordset[0].UserName);
                                }
                            });
                        }
                    });
                });
				
				
				
				
				
				//Add Donator Admin
				//POST API
app.post("/api/dreg/user/android/post/:Email/:ContactNo/:Name/:Gender/:Address/:City/:Profession", function (req, res) {
    var query = "INSERT INTO [DReg] (Name,Gender,Address,City,Profession,ContactNo,Email,Status,UserName,Password,PracticeId) VALUES ('" + req.params.Name + "','" + req.params.Gender + "','" + req.params.Address + "','" + req.params.City + "','" + req.params.Profession + "','" + req.params.ContactNo + "','" + req.params.Email + "','True','0','0','0')";

    var queryCheck = "select * from DReg where Email = '" + req.params.Email + "' or ContactNo = '" + req.params.ContactNo + "'";
    sql.connect(dbConfig, function (err) {
        if (err) {
            console.log("Error while connecting database :- " + err);
            res.send(err);
        }
        else {
            //Connection success notification
            console.log("Successfully connected to the " + dbConfig.database);
            //Connection success notification

            // create Request object
            var request = new sql.Request();

            // query to the database
            request.query(queryCheck, function (err, data) {
                if (err) {
                    console.log("Error while querying database :- " + err);
                    res.send("failure");
                }
                else {

				var checkArray = data.recordset;
				if(checkArray.length<=0){
	                // query to the database
                    request.query(query, function (err, data) {
                    if (err) {
                    console.log("Error while querying database :- " + err);
                    res.send("failure during insertion");
                    }
                    else {
                    res.send("success");
                    }
                    });

				}else{
				res.send("this contact or email already exists!");
					}



                    //res.send(checkArray);
                    //res.send("success");
                }
            });


        }
    });
});
   
   
   
   
   
   
   
				//Permanent Donator User false
				//POST API
app.post("/api/dreg/permanentuser/android/post/:Email/:ContactNo/:Name/:Gender/:Address/:City/:Profession", function (req, res) {
    var query = "INSERT INTO [DReg] (Name,Gender,Address,City,Profession,ContactNo,Email,Status,UserName,Password,PracticeId) VALUES ('" + req.params.Name + "','" + req.params.Gender + "','" + req.params.Address + "','" + req.params.City + "','" + req.params.Profession + "','" + req.params.ContactNo + "','" + req.params.Email + "','False','0','0','0')";

    var queryCheck = "select * from DReg where Email = '" + req.params.Email + "' or ContactNo = '" + req.params.ContactNo + "'";
    sql.connect(dbConfig, function (err) {
        if (err) {
            console.log("Error while connecting database :- " + err);
            res.send(err);
        }
        else {
            //Connection success notification
            console.log("Successfully connected to the " + dbConfig.database);
            //Connection success notification

            // create Request object
            var request = new sql.Request();

            // query to the database
            request.query(queryCheck, function (err, data) {
                if (err) {
                    console.log("Error while querying database :- " + err);
                    res.send("failure");
                }
                else {

				var checkArray = data.recordset;
				if(checkArray.length<=0){
	                // query to the database
                    request.query(query, function (err, data) {
                    if (err) {
                    console.log("Error while querying database :- " + err);
                    res.send("failure during insertion");
                    }
                    else {
                    res.send("success");
                    }
                    });

				}else{
				res.send("this contact or email already exists!");
					}



                    //res.send(checkArray);
                    //res.send("success");
                }
            });


        }
    });
});






				//Volunteer User true
				//POST API
app.post("/api/volunteeruser/android/post/:Email/:ContactNo/:Name/:Address/:Date/:Profession/:Gender/:Listen/:Reason/:Image/:Blood", function (req, res) {
    var query = "INSERT INTO [VolunteerRegistration2] (VName,Address,DateOfBirth,Email,Profession,Gender,ContactNo,FromWhereYouLearnAboutUs,TheReasonOfYourVolunteering,Uimg,Blood,aStatus,UserName,Password,PracticeId) VALUES ('" + req.params.Name + "','" + req.params.Address + "','" + req.params.Date + "','" + req.params.Email + "','" + req.params.Profession + "','" + req.params.Gender + "','" + req.params.ContactNo + "','" + req.params.Listen + "','" + req.params.Reason + "','" + req.params.Image + "','" + req.params.Blood + "','True','0','0','0')";

    var queryCheck = "select * from VolunteerRegistration2 where Email = '" + req.params.Email + "' or ContactNo = '" + req.params.ContactNo + "'";
    sql.connect(dbConfig, function (err) {
        if (err) {
            console.log("Error while connecting database :- " + err);
            res.send(err);
        }
        else {
            //Connection success notification
            console.log("Successfully connected to the " + dbConfig.database);
            //Connection success notification

            // create Request object
            var request = new sql.Request();

            // query to the database
            request.query(queryCheck, function (err, data) {
                if (err) {
                    console.log("Error while querying database :- " + err);
                    res.send("Failed");
                }
                else {

				var checkArray = data.recordset;
				if(checkArray.length<=0){
	                // query to the database
                    request.query(query, function (err, data) {
                    if (err) {
                    console.log("Error while querying database :- " + err);
                    res.send("failure during insertion");
                    }
                    else {
                    res.send("Registration Successful");
                    }
                    });

				}else{
				res.send("this contact or email already exists!");
					}



                    //res.send(checkArray);
                    //res.send("success");
                }
            });


        }
    });
});










				//volunteer user true body dea

				//POST API
app.post("/api/volunteeruser/android/post/body/", function (req, res) {
    var query = "INSERT INTO [VolunteerRegistration2] (VName,Address,DateOfBirth,Email,Profession,Gender,ContactNo,FromWhereYouLearnAboutUs,TheReasonOfYourVolunteering,Uimg,Blood,aStatus,UserName,Password,PracticeId) VALUES ('" + req.body.VName + "','" + req.body.Address + "','" + req.body.DateOfBirth + "','" + req.body.Email + "','" + req.body.Profession + "','" + req.body.Gender + "','" + req.body.ContactNo + "','" + req.body.FromWhereYouLearnAboutUs + "','" + req.body.TheReasonOfYourVolunteering + "','" + req.body.Uimg + "','" + req.body.Blood + "','True','0','0','0')";

    var queryCheck = "select * from VolunteerRegistration2 where Email = '" + req.body.Email + "' or ContactNo = '" + req.body.ContactNo + "'";
    sql.connect(dbConfig, function (err) {
        if (err) {
            console.log("Error while connecting database :- " + err);
            res.send(err);
        }
        else {
            //Connection success notification
            console.log("Successfully connected to the " + dbConfig.database);
            //Connection success notification

            // create Request object
            var request = new sql.Request();

            // query to the database
            request.query(queryCheck, function (err, data) {
                if (err) {
                    console.log("Error while querying database :- " + err);
                    res.send("Failed");
                }
                else {

				var checkArray = data.recordset;
				if(checkArray.length<=0){
	                // query to the database
                    request.query(query, function (err, data) {
                    if (err) {
                    console.log("Error while querying database :- " + err);
                    res.send("failure during insertion");
                    }
                    else {
                    res.send("Registration Successful");
                    }
                    });

				}else{
				res.send("this contact or email already exists!");
					}



                    //res.send(checkArray);
                    //res.send("success");
                }
            });


        }
    });
});



//volunteer user false body dea

				//POST API
app.post("/api/volunteeruser/android/post/body/false/", function (req, res) {
    var query = "INSERT INTO [VolunteerRegistration2] (VName,Address,DateOfBirth,Email,Profession,Gender,ContactNo,FromWhereYouLearnAboutUs,TheReasonOfYourVolunteering,Uimg,Blood,aStatus,UserName,Password,PracticeId) VALUES ('" + req.body.VName + "','" + req.body.Address + "','" + req.body.DateOfBirth + "','" + req.body.Email + "','" + req.body.Profession + "','" + req.body.Gender + "','" + req.body.ContactNo + "','" + req.body.FromWhereYouLearnAboutUs + "','" + req.body.TheReasonOfYourVolunteering + "','" + req.body.Uimg + "','" + req.body.Blood + "','False','0','0','0')";

    var queryCheck = "select * from VolunteerRegistration2 where Email = '" + req.body.Email + "' or ContactNo = '" + req.body.ContactNo + "'";
    sql.connect(dbConfig, function (err) {
        if (err) {
            console.log("Error while connecting database :- " + err);
            res.send(err);
        }
        else {
            //Connection success notification
            console.log("Successfully connected to the " + dbConfig.database);
            //Connection success notification

            // create Request object
            var request = new sql.Request();

            // query to the database
            request.query(queryCheck, function (err, data) {
                if (err) {
                    console.log("Error while querying database :- " + err);
                    res.send("Failed");
                }
                else {

				var checkArray = data.recordset;
				if(checkArray.length<=0){
	                // query to the database
                    request.query(query, function (err, data) {
                    if (err) {
                    console.log("Error while querying database :- " + err);
                    res.send("failure during insertion");
                    }
                    else {
                    res.send("Registration Successful");
                    }
                    });

				}else{
				res.send("this contact or email already exists!");
					}



                    //res.send(checkArray);
                    //res.send("success");
                }
            });


        }
    });
});



















                                        //      ___       ___       ___       ___       ___            ___       ___       ___   
                                        //     /\  \     /\__\     /\  \     /\__\     /\__\          /\__\     /\  \     /\__\  
                                        //     \:\  \   /:/__/_   /::\  \   /:| _|_   /:/ _/_        |::L__L   /::\  \   /:/ _/_ 
                                        //     /::\__\ /::\/\__\ /::\:\__\ /::|/\__\ /::-"\__\       |:::\__\ /:/\:\__\ /:/_/\__\
                                        //    /:/\/__/ \/\::/  / \/\::/  / \/|::/  / \;:;-",-"       /:;;/__/ \:\/:/  / \:\/:/  /
                                        //    \/__/      /:/  /    /:/  /    |:/  /   |:|  |         \/__/     \::/  /   \::/  / 
                                        //               \/__/     \/__/     \/__/     \|__|                    \/__/     \/__/  

