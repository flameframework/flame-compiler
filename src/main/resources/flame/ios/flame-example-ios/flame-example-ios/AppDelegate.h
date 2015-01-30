<#-- TODO remove necessity to add file attribute here -->
<&output file="AppDelegate.h" overwrite="false">
//
//  AppDelegate.h
//  flame-ios
//
//  Created by Michel Vollebregt on 27-01-15.
//  Copyright (c) 2015 ORGANIZATIONNAME. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <CoreData/CoreData.h>

@interface AppDelegate : UIResponder <UIApplicationDelegate>

@property (strong, nonatomic) UIWindow *window;

@property (readonly, strong, nonatomic) NSManagedObjectContext *managedObjectContext;
@property (readonly, strong, nonatomic) NSManagedObjectModel *managedObjectModel;
@property (readonly, strong, nonatomic) NSPersistentStoreCoordinator *persistentStoreCoordinator;

- (void)saveContext;
- (NSURL *)applicationDocumentsDirectory;


@end

</&output>